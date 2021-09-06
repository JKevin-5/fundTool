<template>
    <div>
        <van-divider content-position="left">实时大盘信息</van-divider>
        <!-- <van-row>
                <van-col span="8" style="background:red">
                    上证指数
                </van-col>
                <van-col span="8" style="background:yellow">
                    深圳成指
                </van-col>
                <van-col span="8" style="background:green">
                    港股指数
                </van-col>
        </van-row> -->
        <van-cell-group inset >
            <van-cell v-for="stockindex in stockindex.data" :key="stockindex">{{stockindex.name}} : {{stockindex.value}}  日涨跌幅：{{stockindex.fall_percent}}%</van-cell>
        </van-cell-group>
        <!-- <canvas id="myChart" width="350" height="260"></canvas> -->
    </div>
</template>
<script>
import {getCurrentInstance,nextTick, onBeforeMount,ref} from 'vue'
import {getStockIndex} from '@/network/out_search'
export default({
    setup(){
        const {proxy} = getCurrentInstance();
        const stockindex = ref({
            code:['s_sh000001','s_sz399001','sh000300','sz399006'],
            data:[]
        })
        const data = [
                { genre: 'Sports', sold: 275 },
                { genre: 'Strategy', sold: 115 },
                { genre: 'Action', sold: 120 },
                { genre: 'Shooter', sold: 350 },
                { genre: 'Other', sold: 150 },
                ];
        //等待dom加载完成再生成F2图
        nextTick(()=>{
            const chart = new proxy.F2.Chart({
            id: 'myChart',
            pixelRatio: window.devicePixelRatio // 指定分辨率
            });

            // Step 2: 载入数据源
            console.log(data);
            chart.source(data);

            // Step 3：创建图形语法，绘制柱状图，由 genre 和 sold 两个属性决定图形位置，genre 映射至 x 轴，sold 映射至 y 轴
            chart.interval().position('genre*sold').color('genre');

            // Step 4: 渲染图表
            chart.render();
        });
        const search= async ()=>{
            for(var i=0;i<stockindex.value.code.length;i++){
                var res = await getStockIndex(stockindex.value.code[i]).then(res=>{
                        return res;
                    })
                let strs= res.data.substring(23).split(',');
                var array={};
                array.name=strs[0];
                array.value=strs[1];
                array.fall_value=strs[2];
                array.fall_percent=strs[3];
                array.total=strs[5].substring(0,strs[5].lenght);
                
                stockindex.value.data.push(array);
            }
        }
        onBeforeMount(()=>{
            search();
            
            
        })
        return{
            stockindex
        }
  },
})
</script>
