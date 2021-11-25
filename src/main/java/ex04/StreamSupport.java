package ex04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class StreamSupport {

    public static <T, C extends Iterable<T>> Pipeline<T, T, T> createStream(C collection) {
        return new Pipeline<T, T, T>(collection.iterator());
    }

    protected static class Pipeline<IN, OUT, T> {

        protected final Iterator<T> iterator;
        protected final Pipeline previousStage;
        protected final int depth;

        protected Pipeline(Iterator<T> iterator) {
            this.iterator = iterator;
            this.previousStage = null;
            this.depth = 0;
        }

        protected Pipeline(Pipeline previousStage) {
            this.iterator = previousStage.iterator;
            this.previousStage = previousStage;
            this.depth = previousStage.depth + 1;
        }

        protected ChainedReference<IN, OUT> chainConsumerToCurrentPipe(Consumer<OUT> consumer) {
            throw new IllegalStateException();
        }

        protected Consumer<T> chainAllConsumersInThePipeline(Consumer<OUT> consumer) {
            Objects.requireNonNull(consumer);

            Pipeline p = this;

            while (p.depth > 0) {
                consumer = p.chainConsumerToCurrentPipe(consumer);
                p = p.previousStage;
            }

            return (Consumer<T>) consumer;
        }

        public double average(){
            ArrayList<Double> points = new ArrayList<Double>();
            Consumer<Double> collectingConsumer = new Consumer<Double>() {
                @Override
                public void accept(Double out) {
                    if(out != null){

                        points.add(out);
                    }
                }
            };

            int numberOfPoint = points.size();
            Double pointsSum = 0.0;
            for(int i = 0; i < points.size(); i++){
                pointsSum += points.get(i);
            }

            return pointsSum / numberOfPoint;
        }

    }

    protected static abstract class ChainedReference<T, OUT> implements Consumer<T> {

        protected final Consumer<OUT> downstream;

        public ChainedReference(Consumer<OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }
    }
}