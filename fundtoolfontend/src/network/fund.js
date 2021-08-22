import {get,post,deletes,put} from './axios'

//搜索功能 单个基金信息
export const getFund=() =>get('/fund/'+id);