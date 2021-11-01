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
            <van-cell v-for="stockindex in stockindex.data" :key="stockindex">
                {{stockindex.name}} : {{stockindex.price}}  日涨跌幅：{{stockindex.changePercent}}%
            </van-cell>
        </van-cell-group>
        <!-- <canvas id="myChart" width="350" height="260"></canvas> -->
    </div>
</template>
<script>
import {getCurrentInstance,nextTick, onBeforeMount,ref} from 'vue'
import {getStock} from '@/network/out_search'
export default({
    setup(){
        const {proxy} = getCurrentInstance();
        const stockindex = ref({
            code:['s_sh000001','s_sz399001'],
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

        //查询大盘股票信息
        const search= ()=>{
            getStock('sh000001,sz399001,sz399006').then(res=>{
                stockindex.value.data=res.data.data;
                console.log(stockindex.value.data);
            })
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
