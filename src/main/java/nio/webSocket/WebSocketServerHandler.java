package nio.webSocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import sun.net.www.http.KeepAliveStream;

import javax.xml.stream.events.Characters;
import java.util.Date;

import static io.netty.channel.ChannelFutureListener.CLOSE;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;

/**
 * Created by ${xzl} on 2017/11/3.
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler {
    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("--WebSocketServerHandler ："+msg);
        //传统的http请求
        if(msg instanceof FullHttpRequest){
            handlerHttpRequest(ctx, (FullHttpRequest) msg);
        }else if(msg instanceof WebSocketFrame){
            //websocket 接入
            handlerWebSocketRequest(ctx, (WebSocketFrame) msg);

        }
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        System.out.println("--服务端--刷新---");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(CLOSE);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        System.out.println("--服务端--异常---");
        cause.printStackTrace();
        ctx.close();
    }
    private void handlerWebSocketRequest(ChannelHandlerContext ctx, WebSocketFrame msg) {
        // 判断是否关闭链路
        if(msg instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) msg.retain());
            return;
        }
        // 判断是否 ping 消息
        if(msg instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
            return;
        }
        //文本消息
        if(!(msg instanceof  TextWebSocketFrame)){
            throw  new RuntimeException(String.format("%s 不支持的类型",msg.getClass().getName()));
        }
        // 返回应答消息
        String request = ((TextWebSocketFrame)msg).text();
        ctx.channel().write(new TextWebSocketFrame(request+" 欢迎使用NettyWebSocket,现在时间:"+new Date()));
    }

    private void handlerHttpRequest(ChannelHandlerContext ctx, FullHttpRequest msg) {
        if(!msg.getDecoderResult().isSuccess()||(!"websocket".equals(msg.headers().get("Upgrade")))){
            //http 请求异常
            sendHttpResponse(ctx,msg,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //构造握手 本机
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory
                ("ws://localhost:8080",null,false);
        handshaker = wsFactory.newHandshaker(msg);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            handshaker.handshake(ctx.channel(),msg);
            sendHttpResponse(ctx,msg,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse
            response) {
        if(response.getStatus().code()!=200){
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.getStatus().toString().getBytes());
            response.content().writeBytes(byteBuf);
            byteBuf.release();
        }
        //非 keep-alive 关闭连接
        ChannelFuture future;
        future = ctx.channel().writeAndFlush(response);
        if(!isKeepAlive(request)||response.getStatus().code()!=200){
            future.addListener(CLOSE);
        }
    }
}
