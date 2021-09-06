import {post} from './out_axios'

//查询股票指数
export const getStockIndex=(p) =>post('/?list='+p);