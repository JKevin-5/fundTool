import {get} from './tiantian_axios'

//查询股票指数
export const getStockIndex=(p) =>get('/?code='+p);