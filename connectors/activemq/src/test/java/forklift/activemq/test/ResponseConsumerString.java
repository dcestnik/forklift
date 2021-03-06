package forklift.activemq.test;

import forklift.connectors.ForkliftMessage;
import forklift.decorators.OnMessage;
import forklift.decorators.Response;
import forklift.producers.ForkliftResultResolver;
import forklift.source.decorators.Queue;

import javax.jms.JMSException;

@Queue("response")
public class ResponseConsumerString {
    static ForkliftResultResolver<String> resolver;

    @forklift.decorators.Message
    private ForkliftMessage m;

    @forklift.decorators.Message
    private String s;

    @OnMessage
    public void go() throws JMSException {
        if ("test".equals(s)) {
            resolver.resolve(m.getId(), s);
            throw new RuntimeException("This is expected");
        }
    }

    @Response
    public String response() {
        return "test";
    }
}
