package com.taobao.meta.test;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.common.lang.StringUtil;

/**
 * meta���ɲ���_topicΪ��
 * 
 * @author gongyangyu(gongyangyu@taobao.com)
 * 
 */

public class BlankTopicTest extends BaseMetaTest {

	private final String topic = " ";

	@Test(expected = IllegalArgumentException.class)
	public void sendConsume() throws Exception {
		createProducer();
		producer.publish(this.topic);
		// �����߱���ָ������
		createConsumer("group1");
		try {
			// ������Ϣ
			final int count = 5;
			sendMessage(count, "hello", this.topic);
			Assert.fail();
			// ���Ľ�����Ϣ����֤������ȷ
			subscribe(this.topic, 1024 * 1024, count);

		} catch (IllegalArgumentException e) {
			Assert.assertTrue(StringUtil.contains(e.getMessage(), "Blank topic"));
			e.printStackTrace();
			throw e;
		} finally {
			producer.shutdown();
			consumer.shutdown();
		}

	}
}
