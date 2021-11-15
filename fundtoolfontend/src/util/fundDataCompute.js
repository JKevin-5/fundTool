//总的持仓金额
export const sumFunds = (funds) =>{
    var total=0;
    for(var i=0;i<funds.lengh;i++){
        total=total+funds.amount;
    }
    return total;
}

/**
 * 基金的总金额,三点后进行更新
 */
export const fundAmount = (funds) =>{
    
}

//基金的总盈亏百分比

//基金的每日盈亏数额

