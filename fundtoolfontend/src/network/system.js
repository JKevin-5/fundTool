import {get} from './axios'
//登录
export const getToken = (username,password) => get('/user/login?userName='+username+'&userPassWord='+password);