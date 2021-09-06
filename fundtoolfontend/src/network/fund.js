import {post} from './axios'

//模糊搜索功能 单个基金信息
export const getFund=(p) =>post('/findFunds/'+p);
