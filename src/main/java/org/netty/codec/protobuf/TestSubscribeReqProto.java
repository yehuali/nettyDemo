package org.netty.codec.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }

    private static SubscribeRespProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeRespProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeRespProto.SubscribeReq createSubscribeReq(){
        SubscribeRespProto.SubscribeReq.Builder builder = SubscribeRespProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);

       return builder.build();
    }
}
