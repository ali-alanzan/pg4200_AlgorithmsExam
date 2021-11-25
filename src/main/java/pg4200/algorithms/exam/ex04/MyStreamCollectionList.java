package pg4200.algorithms.exam.ex04;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class MyStreamCollectionList<T> extends MyIterableLinkedList<T> implements MyStreamCollection<T> {

    @Override
    public MyStream<T> stream() {
        return MyStreamSupport.createStream(this);
    }
}
