<template>
  <div class="about">
    <h1>This is an about page</h1>

    <a-button type="primary">
      测试
    </a-button>

    <TwitterOutlined />
    <PlayCircleOutlined />

    <a-space>
      <HomeOutlined />
      <SettingFilled />
      <SmileOutlined />
      <SyncOutlined spin />
      <SmileOutlined :rotate="180" />
      <LoadingOutlined />
    </a-space>
  </div>
</template>

<script setup>
import {
  TwitterOutlined,
  PlayCircleOutlined,
  HomeOutlined,
  SettingFilled,
  SmileOutlined,
  SyncOutlined,
  LoadingOutlined
} from '@ant-design/icons-vue'
</script>
