<template>
  <n-layout style="height: 100vh">
    <n-layout-header style="height: 60px; padding: 24px;" bordered
      >Kevin' Space</n-layout-header
    >
    <n-layout position="absolute" style="top: 60px;" has-sider>
      <n-layout-sider
        bordered
        show-trigger
        collapse-mode="width"
        :collapsed-width="64"
        :width="240"
        :native-scrollbar="false"
        style="max-height: calc(100vh-60px);"
      >
        <n-menu
          :collapsed-width="64"
          :collapsed-icon-size="22"
          :options="menuOptions"
        />
      </n-layout-sider>
      <n-layout content-style="padding: 24px;" :native-scrollbar="false">
        <router-view v-slot="{ Component }">
          <keep-alive>
            <component :is="Component" v-if="ifShow"/>
          </keep-alive>
          <component :is="Component"  v-if="!ifShow"/>
        </router-view> 
      </n-layout>
    </n-layout>
  </n-layout>
</template>
<script>
import { h,defineComponent, onMounted, watch } from 'vue'
import { NIcon } from 'naive-ui'
import router from '@/router'
import {
  BookOutline as BookIcon
} from '@vicons/ionicons5'
import { RouterLink } from 'vue-router'
function renderIcon (icon) {
  return () => h(NIcon, null, { default: () => h(icon) })
}
export default defineComponent({
  
  setup() {
    // const proxy = getCurrentInstance();
    var ifShow = false;
    onMounted(()=>{
      // console.log(router.currentRoute.value.meta.keepAlive);
    })
    watch(router.currentRoute,()=>{
      const route = router.currentRoute.value;
      ifShow = route.meta?.keepAlive;
      console.log(ifShow);
    })
    return {
      ifShow,
      menuOptions:[
        {
          label: '功能集合',
          key: 'hear-the-wind-sing',
          icon: renderIcon(BookIcon)
        },{
          label: '基金',
          key: 'dance-dance-dance',
          icon: renderIcon(BookIcon),
          children: [
            { label: ()=>
                h(
                  RouterLink,
                  {
                    to: {
                      path: '/home/funds'
                    }
                  },
                  { default:() => '基金信息查询'}
                ),
              key: 'a-wild-sheep-chase'
            },{ label: ()=>
                h(
                  RouterLink,
                  {
                    to: {
                      path: '/home/fundHis'
                    }
                  },
                  { default:() => '基金历史信息查询'}
                ),
              key: 'fund—his'
            }
          ]
        }
      ]
    }
  },
})
</script>
