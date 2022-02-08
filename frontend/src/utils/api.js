import axios from "axios";

let __config = {
  baseURL: process.env.VUE_APP_AXIOS_BASE_URL,
};

const __http = axios.create(__config);
__http.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);
__http.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    return Promise.reject(error);
  }
);

export default __http;
