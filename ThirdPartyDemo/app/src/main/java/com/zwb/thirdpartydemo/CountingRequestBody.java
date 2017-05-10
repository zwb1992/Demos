package com.zwb.thirdpartydemo;

/**
 * Created by zwb
 * Description
 * Date 17/4/21.
 */

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * 用来实现上传显示进度
 */
public class CountingRequestBody extends RequestBody{
    private RequestBody delegate;
    private Listener listener;

    public CountingRequestBody(RequestBody delegate, Listener listener) {
        this.delegate = delegate;
        this.listener = listener;
    }

    public CountingRequestBody() {
        super();
    }

    @Override
    public long contentLength() throws IOException {
        try {
            return delegate.contentLength();
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public MediaType contentType() {
        return delegate.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        CountingSink countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);
        delegate.writeTo(bufferedSink);
    }

    protected final class CountingSink extends ForwardingSink{
        private long byteWriten;
        public CountingSink(Sink delegate){
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            byteWriten+=byteCount;
            listener.onRequestCounting(byteWriten,contentLength());
        }
    }


    public static interface Listener{
        public void onRequestCounting(long byteWriten,long contentLength);
    }

}
