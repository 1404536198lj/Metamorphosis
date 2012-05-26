package com.taobao.meta.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * meta���ɲ���_���Ĳ����ڵ�topic
 * 
 * @author gongyangyu(gongyangyu@taobao.com)
 * 
 */

import com.taobao.metamorphosis.exception.MetaClientException;

public class RandomTopicTest extends BaseMetaTest {

	private final String topic = "gongyangyu";

	@Test
	public void sendConsume() throws Exception {
		createProducer();
		producer.publish(this.topic);
		// �����߱���ָ������
		createConsumer("group1");

		try {
			// ������Ϣ
			final int count = 5;
			sendMessage(count, "hello", this.topic);
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
