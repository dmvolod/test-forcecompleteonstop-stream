package com.mycompany.camel;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MyCamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("stream:file?fileName=src/main/resources/test.xml")
            // from("file://src/main/resources/?fileName=test.xml&noop=true")
            .aggregate(constant(true), new CamelAggregator()).completionSize(50).forceCompletionOnStop().process(new Processor() {

                public void process(Exchange exchange) throws Exception {
                    List<Object> statusObjects = exchange.getIn().getBody(List.class);
                    System.out.println(statusObjects.size());
                    // System.out.println(statusObjects);
                }

            }).end();
    }

}
