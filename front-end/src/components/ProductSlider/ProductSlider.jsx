import React, { useRef, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";

import "./productSlider.css";
import "swiper/swiper.min.css";
import "swiper/css/pagination";
import "swiper/css/navigation";

import { Autoplay, Pagination, Navigation } from "swiper";

const ProductSlider = ({product}) => {

  const progressCircle = useRef(null);
  const progressContent = useRef(null);
  const onAutoplayTimeLeft = (s, time, progress) => {
    progressCircle.current.style.setProperty("--progress", 1 - progress);
    progressContent.current.textContent = `${Math.ceil(time / 1000)}s`;
  };
  return (
    <div className="containerSlider">
      <Swiper
        spaceBetween={30}
        centeredSlides={true}
        autoplay={{
          delay: 2500,
          disableOnInteraction: false,
        }}
        pagination={{
          type: "fraction",
        }}
        navigation={true}
        modules={[Autoplay, Pagination, Navigation]}
        onAutoplayTimeLeft={onAutoplayTimeLeft}
        className="mySwiper"
      >
        <SwiperSlide>
          <img
            src={product?.listImagen[0].url}
            alt=""
          />
        </SwiperSlide>
        <SwiperSlide>
          <img
            src={product?.listImagen[1].url}
            alt=""
          />
        </SwiperSlide>
        <SwiperSlide>
          <img
            src={product?.listImagen[2].url}
            alt=""
          />
        </SwiperSlide>
        <SwiperSlide>
          <img src={product?.listImagen[3].url} alt="" />
        </SwiperSlide>
        <SwiperSlide>
          <img src={product?.listImagen[4].url} alt="" />
        </SwiperSlide>
        <div className="autoplay-progress" slot="container-end">
          <svg viewBox="0 0 48 48" ref={progressCircle}>
            <circle cx="24" cy="24" r="20"></circle>
          </svg>
          <span ref={progressContent}></span>
        </div>
      </Swiper>
    </div>
  );
};

export default ProductSlider;
