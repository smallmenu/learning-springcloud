package com.niuchaoqun.springcloud.eureka.consumer.feign;

import com.niuchaoqun.springcloud.commons.rest.RestJson;
import com.niuchaoqun.springcloud.eureka.consumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 使用 Feign 几乎会屏蔽所有的异常消息
 *
 */
@FeignClient(name = "EUREKA-CLIENT", fallback = EurekaClientFallback.class)
public interface EurekaClient {
    /**
     * GET 必须使用 @RequestParam 注解，表明字段名
     *
     * 如果服务端使用了 @RequestParam，那么 Feign 也必须使用 @RequestParam 注解，否则直接请求失败 提示：'POST' not supported
     *
     * （猜测匹配不到字段名） Feign 会自动把这种方式转换成 POST 请求
     *
     * 为保持统一，服务端与客户端应强制使用 @RequestParam
     *
     * 但是 RestTemplate 通过构造 URL 参数可正常请求
     *
     * @param param1
     * @param param2
     * @return
     */
    @GetMapping("/get_param")
    String getParam(@RequestParam(value = "param1", required = false) String param1, @RequestParam(value = "param2", required = false) String param2);

    /**
     * GET 不支持对象传递，直接请求失败，提示：'POST' not supported
     *
     * 猜测 Feign 会自动把这种方式转换成 POST 请求
     *
     * 但是 RestTemplate 可以通过构造 URL 参数可正常请求，所以为了统一避免这种使用方式
     *
     * @param user
     * @return
     */
    @GetMapping("/get_object")
    String getObject(User user);

    /**
     *
     * @param path
     * @return
     */
    @GetMapping("/get_path/{path}")
    String getPath(@PathVariable(value = "path") String path, @RequestParam(value = "param") String param);

    /**
     * POST 如果没有注解，会默认使用 @RequestBody，并且通过 payload 传递数据，服务提供方必须使用 @RequestBody 来接受数据
     *
     * RestTemplate 如果传递的是实体，也是 payload 传递
     *
     * @param user
     * @return
     */
    @PostMapping("/post_object")
    String postObject(User user);

    /**
     *  POST 使用 @RequestParam，服务端无法接收到数据
     *
     *  猜测 Feign 对于实体类，默认会使用 payload 传递数据
     *
     *  RestTemplate 使用 LinkedMultiValueMap 同样无法接收到数据
     *
     *  所以如果是实体类，应当统一使用 payload 传递
     *
     * @param user
     * @return
     */
    @PostMapping("/post_form")
    String postForm(@RequestParam(value = "user") User user);

    /**
     * POST 通过  @RequestParam 注解可以很好的支持多个 form-data 形式的请求
     *
     * 但是 RestTemplate 如果传递的是实体，默认是 payload，所以无法接收不到 form-data 数据，需要通过 LinkedMultiValueMap 改造
     *
     * @param param1
     * @param param2
     * @return
     */
    @PostMapping("/post_param")
    String postParam(@RequestParam(value = "param1") String param1, @RequestParam(value = "param2") String param2);

    /**
     * POST 如果是个混合参数，（假想需求）
     *
     * Feign 无论使用 @RequestParam 还是 @RequestBody， 服务端都接受不到 user 的数据，但能传递 param2
     *
     * RestTemplate 可以通过服务端注解 @RequestBody 传递 user，但是 param2 不知道怎么传递，找不到姿势
     *
     * 所以避免使用这种方式
     *
     * @param user
     * @param param2
     * @return
     */
    @PostMapping("/post_param")
    String postMixParam(@RequestBody User user, @RequestParam(value = "param2", required = false) String param2);

    /**
     * Feign 能接收到 DELETE 的返回请求体
     *
     * RestTemplate.deleteForObject 无法接受请求体，需要使用 exchange 改造
     *
     * @return
     */
    @DeleteMapping("/remove")
    RestJson remove();

    @GetMapping("/get")
    String get();

    @GetMapping("/get_sleep")
    String getSleep();


}
