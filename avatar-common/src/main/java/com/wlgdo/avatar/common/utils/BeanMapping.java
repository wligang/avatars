//package com.wlgdo.avatar.common.utils;
//
//import java.util.Collection;
//import java.util.function.BiConsumer;
//
///**
// * Author: Ligang.Wang[wlgchun@l63.com]
// * Date: 2019/6/15 2:25
// */
//public class BeanMapping {
//    public static <S, D> void map(Collection<S> source, Collection<D> destination, Class<D> dstClass, BiConsumer<S, D> biConsumer) {
//        for (S s : source) {
//            destination.add(map(s, dstClass, biConsumer));
//        }
//    }
//
//    public static <S, D> D map(S source, Class<D> dstClass, BiConsumer<S, D> biConsumer) throws Exception {
//        if (source == null) {
//            return null;
//        }
//        try {
//            D dstObject = getInstance().map(source, dstClass);
//            if (dstObject != null && biConsumer != null) {
//                biConsumer.accept(source, dstObject);
//            }
//            return dstObject;
//        } catch (Exception e) {
//            logger.error("对象映射出错, 原对象类型: {}, 目标对象类型: {}", source.getClass(), dstClass);
//            throw new Exception(e);
//        }
//    }
//}
