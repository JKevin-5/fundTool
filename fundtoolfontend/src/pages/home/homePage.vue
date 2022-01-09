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
        <!-- 这是vue3的写法，与vue2的写法有所区别，如果这里使用vue2.x写法，会有警告 -->
        <router-view v-slot="{ Component }">
          <keep-alive>
            <component :is="Component" :key="$route.name" v-if="$route.meta.keepAlive" />
          </keep-alive>
          <component :is="Component" :key="$route.name" v-if="!$route.meta.keepAlive" />
        </router-view>

      </n-layout>
    </n-layout>
  </n-layout>
</template>
<script>
import { h,defineComponent, onMounted } from 'vue'
import { NIcon } from 'naive-ui'
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
    onMounted(()=>{
      // console.log(router.currentRoute.value.meta.keepAlive);
    })
    return {
      menuOptions:[
        {
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
        },
        {
          label: '个人配置',
          key: 'personal-possession',
          icon: renderIcon(BookIcon),
          children: [
            { label: ()=>
                h(
                  RouterLink,
                  {
                    to: {
                      path: '/home/possesions'
                    }
                  },
                  { default:() => '管理个人配置'}
                ),
              key: 'possesions'
            }
          ]
        },{
          label: '定时任务配置中心',
          key: 'cron-setting',
          icon: renderIcon(BookIcon),
          children: [
            { label: ()=>
                h(
                  RouterLink,
                  {
                    to: {
                      path: '/home/cron'
                    }
                  },
                  { default:() => '定时任务管理'}
                ),
              key: 'cron'
            }
          ]
        }
      ]
    }
  },
})
</script>
