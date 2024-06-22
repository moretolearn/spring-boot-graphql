package com.moretolearn.config;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@Configuration
public class ScalarConfig {

	public static final GraphQLScalarType DateScalar = GraphQLScalarType.newScalar().name("GregorianCalendar")
			.description("A custom scalar that handles Date").coercing(new Coercing() {
				@Override
				public Object serialize(Object dataFetcherResult, GraphQLContext graphQLContext, Locale locale)
						throws CoercingSerializeException {
					try {
						GregorianCalendar publishedTime = (GregorianCalendar) dataFetcherResult;
						Date time = publishedTime.getTime();
						DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
						String date = df.format(time);
						return date;
					} catch (Exception exception) {
						throw new CoercingSerializeException("Invalid Input:" + exception.getMessage());
					}
				}

				@Override
				public Object parseValue(Object input, GraphQLContext graphQLContext, Locale locale)
						throws CoercingParseValueException {
					return null;
				}

				@Override
				public Object parseLiteral(Value input, CoercedVariables variables, GraphQLContext graphQLContext,
						Locale locale) throws CoercingParseLiteralException {
					try {
						StringValue stringValue = (StringValue) input;
						DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
						Date date = df.parse(stringValue.getValue());
						Calendar gregorianCalendar = new GregorianCalendar();
						gregorianCalendar.setTime(date);
						return gregorianCalendar;
					} catch (Exception exception) {
						throw new CoercingParseLiteralException("Invalid Input:" + exception.getMessage());
					}
				}

				@Override
				public Value<?> valueToLiteral(Object input, GraphQLContext graphQLContext, Locale locale) {
					return new StringValue(input.toString());
				}
			}).build();

	@Bean
	public RuntimeWiringConfigurer runtimeWiringConfigurer() {
		return builder -> builder.scalar(DateScalar);
	}
}