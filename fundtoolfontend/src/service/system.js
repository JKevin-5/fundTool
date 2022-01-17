import {get} from './axios'

const BASE_URL = '/api'
//登录
export const getToken = (username,password) => get(BASE_URL+'/user/login?userName='+username+'&userPassWord='+password);