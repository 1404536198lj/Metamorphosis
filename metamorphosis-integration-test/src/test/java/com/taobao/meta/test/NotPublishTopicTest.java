package com.taobao.meta.test;

import org.junit.Assert;
import org.junit.Test;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * meta���ɲ���_δ����topic
 * 
 * @author gongyangyu(gongyangyu@taobao.com)
 * 
 */

public class NotPublishTopicTest extends BaseMetaTest {

	private final String topic = "meta-test";

	@Test
	public void sendConsume() throws Exception {
		createProducer();
		// �����߱���ָ������
		createConsumer("group1");
		try {
			// ������Ϣ
			final int count = 5;
			sendMessage(count, "hello", this.topic);
			Assert.fail();
			// ���Ľ�����Ϣ����֤������ȷ
			subscribe(this.topic, 1024 * 1024, count);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof MetaClientException);
			Assert.assertTrue(e.getMessage().indexOf("send message failed") != -1);
		} finally {
			producer.shutdown();
			consumer.shutdown();
		}

	}
}
