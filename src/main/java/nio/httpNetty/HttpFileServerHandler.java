package nio.httpNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

/**
 * Created by ${xzl} on 2017/10/31.
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        System.out.println("客户端接到  ："+msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        System.out.println("*****************************");
        //messageReceived
        if(!request.getDecoderResult().isSuccess()){
            System.out.println("请求失败！500");
            return;
        }
        if(request.getMethod().name()!="GET"){
            System.out.println("不支持get");
            return;
        }
        String uri = request.getUri();
        if(uri==null){
            System.out.println("路径错误");
            return;
        }
        File file  = new File(uri);
        if(file.isHidden()||!file.exists()){
            System.out.println("文件不存在--404");
            return;
        }
        if(file.isFile()){
            System.out.println("不是文件--503");
        }
        //以只读 打开文件
        RandomAccessFile accessFile = new RandomAccessFile(file,"r");
        long length = accessFile.length();
        HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.ACCEPTED);
        response.headers().set((CharSequence) new Object(),HttpHeaders.Values.KEEP_ALIVE);
        ctx.write(response);
        //*******
        ChannelFuture  channelFuture;
        channelFuture = ctx.write(new ChunkedFile(accessFile,0,length,8192),ctx.newPromise());
        channelFuture.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {
                System.out.println("operationProgressed-------开始");
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                System.out.println("operationComplete-------完成");
            }
        });
        ctx.writeAndFlush("sssssces");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("--客户端--异常---");
        cause.printStackTrace();
        ctx.close();
    }
}
