const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/member': {
        target: 'http://localhost:8000', // 你的后端服务地址
        changeOrigin: true,
      }
    }
  }
})
