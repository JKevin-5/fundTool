<template>
    <div class="homePage">
        <div class="header">
            <span>Mobile Info</span>
        </div>
        <div class="main">
            <div class="main_inner">
                <router-view/>
            </div>
        </div>
        <div class="footer">
            <ul>
                <li><router-link to="/home/main">首页</router-link></li>
                <li><router-link to="/home/fundData">功能页</router-link></li>
                <li><router-link to="/home/optionalFunds">个人</router-link></li>
            </ul>
        </div>
    </div> 
</template>
<style scoped>
    .homePage{
        margin:0;
        padding:0;
        height: 100%;
        overflow:hidden;
        
    }
    .header{
        position:fixed;
        display: flex;
        flex-direction: row;
        align-items: center;
        width:100%;
        background-color: black;
        height: 60px;
        box-shadow: 1px -1px 1px #e6e6e6;
        z-index: 100;
    }
    .header span{
        color: white;
        margin-left: 30px;
    }
    .main{
        position:absolute !important;
        position:relative;
        width: 100%;
        padding-top: 100px ;
        overflow: hidden;
        height: auto !important;
        height: 100%;
    }
    .main_inner{
        position: relative;
        height: 100%;
        overflow-y: auto;
        bottom: 50px;
    }
    .footer{
        position:fixed;
        bottom: 0px;
        flex-direction: row;
        height: auto;
        width: 100%;
        background-color: #eee;
    }
    .footer ul{
        display: flex;
        list-style: none;
        padding:8px 0;
        margin:0;
        justify-content: space-around;
        
    }

    .footer ul > li a{
        display: block;
        padding: 4px 8px;
        text-decoration: none;
    }
    
</style>