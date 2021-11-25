package pg4200.algorithms.exam.ex04;


/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class MyStreamCollectionHashMap<K,V> extends MyIterableHashMap<K,V> implements MyStreamCollection<V> {

    @Override
    public MyStream<V> stream() {
        return MyStreamSupport.createStream(this);
    }
}
