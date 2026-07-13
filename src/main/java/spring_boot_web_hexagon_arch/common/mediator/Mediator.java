package spring_boot_web_hexagon_arch.common.mediator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mediator {

    Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    //Key is the type of class that refers
    //Value is the class that handles it
    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        requestHandlerMap = requestHandlers.stream().collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
    }

    public <R, T extends Request<R>> R dispatch(T request) {

        RequestHandler<T, R> handler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());
        if (handler == null) {
            throw new RuntimeException("No handler found for request type: " + request.getClass());
        }
        return handler.handle(request);
    }
}
