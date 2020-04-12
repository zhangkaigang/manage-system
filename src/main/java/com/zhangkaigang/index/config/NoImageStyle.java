package com.zhangkaigang.index.config;

import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.util.Configurable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * @Description:自定义实现的图片样式类：Kaptcha 去除无噪点配置
 *
 * @Author:zhang.kaigang
 * @Date:2020/4/10
 * @Version:1.0
 */
public class NoImageStyle extends Configurable implements GimpyEngine {

    /**
     * 原生有三种：水纹 、 鱼眼 、 阴影 ，下面代码就是将原生水纹样式的代码copy然后去掉 各种filter，直接生成图片
     * @param bufferedImage
     * @return
     */
    @Override
    public BufferedImage getDistortedImage(BufferedImage bufferedImage) {
        //NoiseProducer noiseProducer = this.getConfig().getNoiseImpl();
        BufferedImage distortedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 2);
        Graphics2D graphics = (Graphics2D)distortedImage.getGraphics();
        //RippleFilter rippleFilter = new RippleFilter();
        //rippleFilter.setWaveType(0);
        //rippleFilter.setXAmplitude(2.6F);
        //rippleFilter.setYAmplitude(1.7F);
        //rippleFilter.setXWavelength(15.0F);
        //rippleFilter.setYWavelength(5.0F);
        //rippleFilter.setEdgeAction(0);
        //WaterFilter waterFilter = new WaterFilter();
        //waterFilter.setAmplitude(1.5F);
        //waterFilter.setPhase(10.0F);
        //waterFilter.setWavelength(2.0F);
        //BufferedImage effectImage = waterFilter.filter(baseImage, (BufferedImage)null);
        //effectImage = rippleFilter.filter(effectImage, (BufferedImage)null);
        graphics.drawImage(bufferedImage, 0, 0, (Color)null, (ImageObserver)null);
        graphics.dispose();
        //noiseProducer.makeNoise(distortedImage, 0.1F, 0.1F, 0.25F, 0.25F);
        //noiseProducer.makeNoise(distortedImage, 0.1F, 0.25F, 0.5F, 0.9F);
        return distortedImage;
    }
}
