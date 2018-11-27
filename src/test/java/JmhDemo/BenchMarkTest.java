package JmhDemo;

import com.alibaba.fastjson.JSON;
import com.github.bean.InnerMessage;
import com.github.bean.Message;
import com.github.serializer.BaseSerializer;
import com.github.serializer.FastjsonSerializer;
import com.github.serializer.GsonSerializer;
import com.github.serializer.JacksonSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bs.yang on 11/27/2018.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 5, time = 5)
@Fork(2)
@Threads(3)
public class BenchMarkTest {

    private List<Message> result;
    private String str;
    private BaseSerializer baseSerializer;

    @Param({"fastJson", "jackson", "gson"})
    private String jsonLib;

    @Setup(Level.Trial)
    public void preInit() {
        switch (jsonLib) {
            case "fastJson":
                baseSerializer = new FastjsonSerializer();
                break;
            case "jackson":
                baseSerializer = new JacksonSerializer();
                break;
            case "gson":
                baseSerializer = new GsonSerializer();
                break;
        }
    }

    @Setup(Level.Iteration)
    public void prepare() {
        Date date = new Date();
        AtomicLong ids = new AtomicLong(10000000l);
        ArrayList<Double> doubles = new ArrayList<Double>() {{
            add(103D);
            add(104D);
        }};
        result = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            InnerMessage innerMessage = InnerMessage.builder().name("inner name").build();
            Message message = Message.builder().strObj("outer str").floatObj(102f).doubleObjList(doubles)
                    .boolObj(Boolean.TRUE).bytesObj(new byte[]{1, 2, 3}).int32Obj(32).int64Obj(64)
                    .innerMessageObj(innerMessage).createDate(date).id(ids.getAndIncrement()).build();
            result.add(message);
        }
        str = JSON.toJSONString(result);
    }


    @Benchmark
    public String serialize() {
        return baseSerializer.serialize(result);
    }

    @Benchmark
    public Object deserialize() {
        return baseSerializer.deserialize(str, Message.class);
    }


    /**
     * OUTPUT:
     * Benchmark                          (jsonLib)  Mode  Cnt        Score        Error  Units
     * JmhDemo.BenchMarkTest.deserialize   fastJson  avgt   10  1130249.037 ±  83060.730  ns/op
     * JmhDemo.BenchMarkTest.deserialize    jackson  avgt   10  1834273.900 ± 169394.976  ns/op
     * JmhDemo.BenchMarkTest.deserialize       gson  avgt   10  2049374.547 ± 219772.074  ns/op
     * JmhDemo.BenchMarkTest.serialize     fastJson  avgt   10  3150767.844 ± 145423.770  ns/op
     * JmhDemo.BenchMarkTest.serialize      jackson  avgt   10  1026733.912 ± 141252.341  ns/op
     * JmhDemo.BenchMarkTest.serialize         gson  avgt   10  4872197.770 ± 608520.111  ns/op
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchMarkTest.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
