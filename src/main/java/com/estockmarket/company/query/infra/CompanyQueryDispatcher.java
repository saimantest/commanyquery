package com.estockmarket.company.query.infra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.estockmarket.cqrscore.domain.BaseEntity;
import com.estockmarket.cqrscore.infra.QueryDispatcher;
import com.estockmarket.cqrscore.queries.BaseQuery;
import com.estockmarket.cqrscore.queries.QueryHandlerMethod;

@Service
public class CompanyQueryDispatcher implements QueryDispatcher {

	@SuppressWarnings("rawtypes")
	private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
		List<QueryHandlerMethod> handlers = routes.computeIfAbsent(type, e -> new LinkedList<>());
		handlers.add(handler);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <U extends BaseEntity> List<U> send(BaseQuery query) {
		List<QueryHandlerMethod> handlers = routes.get(query.getClass());
		if (handlers == null || handlers.size() <= 0)
			throw new RuntimeException("No query handler is registered.");
		if (handlers.size() > 1)
			throw new RuntimeException("Can not send query to more than 1 handler");
		return handlers.get(0).handle(query);
	}

}
