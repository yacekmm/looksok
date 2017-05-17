package com.looksok.ws.config;

import com.looksok.ws.WsProxy;
import com.looksok.ws.config.WsTestUtils.MyStompFrameHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.CompletableFuture;

import static com.looksok.ws.config.WsTestUtils.MyStompSessionHandler;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WsConfigIntegrationTest {

    @Value("${local.server.port}")
    private int port;
    @Autowired
    private WsProxy wsProxy;

    private WebSocketStompClient stompClient;
    private StompSession stompSession;
    private WsTestUtils wsTestUtils = new WsTestUtils();


    @Before
    public void setUp() throws Exception {
        String wsUrl = "ws://127.0.0.1:" + port + "/ws";
        stompClient = wsTestUtils.createWebSocketClient();
        stompSession = stompClient.connect(wsUrl, new MyStompSessionHandler()).get();
    }

    @After
    public void tearDown() throws Exception {
        stompSession.disconnect();
        stompClient.stop();
    }

    @Test
    public void connectsToSocket() throws Exception {

        assertThat(stompSession.isConnected()).isTrue();
    }

    @Test
    public void receivesMessageFromSubscribedQueue() throws Exception {

        //given
        CompletableFuture<String> resultKeeper = new CompletableFuture<>();

        stompSession.subscribe(
                "/queue/my-id",
                new MyStompFrameHandler((payload) -> resultKeeper.complete(payload.toString())));
        Thread.currentThread().sleep(1000);

        //when
        wsProxy.sendMessage("my-id", "test-payload");

        //then
        assertThat(resultKeeper.get(2, SECONDS)).isEqualTo("test-payload");
    }

}