package com.satishlabs.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import com.satishlabs.domain.Greeting;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GreetingStreamsTopology {
	public static String GREETINGS = "greetings";
	public static String GREETINGS_OUTPUT = "greetings-output";
	
	@Autowired
	public void process(StreamsBuilder streamsBuilder) {
		var greetingStream = streamsBuilder
			.stream(GREETINGS, 
					Consumed.with(Serdes.String(),
							//Serdes.String())
							new JsonSerde<Greeting>())
					);
		
		greetingStream
			//.print(Printed.<String, String>toSysOut().withLabel("greetingStream"));
			.print(Printed.<String, Greeting>toSysOut().withLabel("greetingStream"));
		
		var modifiedStream = greetingStream
				.mapValues((readOnlyKey, value) -> 
				//value.toUpperCase()
				new Greeting(value.message().toUpperCase(), value.timeStamp())
						);
		
		modifiedStream
			//.print(Printed.<String, String>toSysOut().withLabel("greetingStream"));
			.print(Printed.<String, Greeting>toSysOut().withLabel("greetingStream"));
		
		modifiedStream
			.to(GREETINGS_OUTPUT, 
					//Produced.with(Serdes.String(), Serdes.String())
					Produced.with(Serdes.String(), new JsonSerde<Greeting>()));
	}
}
