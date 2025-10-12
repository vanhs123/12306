// src/utils/request.js
import axios from 'axios';

// === 修改成你的后端入口 ===
// 你现在通过 9000 访问网关，并且服务有 context-path /member
const api = axios.create({
    baseURL: 'http://192.168.30.197:9000/member',
    timeout: 10000,
});

// 请求拦截器：自动带上 token
api.interceptors.request.use(
    (config) => {
        const t = localStorage.getItem('token');
        if (t) {
            // 和后端保持一致：网关读取的是 header: token
            config.headers['token'] = t;

            // 如果你改成标准写法，也可以用这一行（择一保留）
            // config.headers['Authorization'] = `Bearer ${t}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

// 响应拦截器：统一处理 401 / 错误提示
api.interceptors.response.use(
    (resp) => resp,
    (error) => {
        const status = error?.response?.status;
        if (status === 401) {
            // 未登录或 token 失效：可跳转到登录页
            // window.location.href = '/login';
            console.warn('未授权（401），请先登录或检查 token');
        }
        return Promise.reject(error);
    }
);

api.interceptors.request.use(cfg => {
    const t = localStorage.getItem('token');
    console.log('[api] token in LS =', t);       // ← 应该能在 Console 看到
    if (t) cfg.headers['token'] = t;             // 网关读的是 token 头
    console.log('[api] headers =', cfg.headers); // ← 这里能看到带了 token
    return cfg;
});


export default api;
