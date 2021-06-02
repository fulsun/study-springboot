package tk.fulsun.demo.message.transaction;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 事务消息监听对象
 * <pre>
 *  执行本地事务和消息回查
 * <pre>
 * @author fulsun
 * @date 2021/6/321:32
 */
public class TransactionListenerImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String transactionId = msg.getTransactionId();
        System.out.println(transactionId + ":执行本地事务");
        if (StringUtils.equals("TagA", msg.getTags())) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else if (StringUtils.equals("TagB", msg.getTags())) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else {
            return LocalTransactionState.UNKNOW;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String transactionId = msg.getTransactionId();
        System.out.println(transactionId + "MQ检查消息Tag【" + msg.getTags() + "】的本地事务执行结果");
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}