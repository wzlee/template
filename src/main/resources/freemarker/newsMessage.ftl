{
    "touser":"${openid!""}",
    "msgtype":"news",
    "news":{
        "articles": [
        <#list messages as message>
         {
             "title":"${message.title!""}",
             "description":"${message.description!""}",
             "url":"${contextPath!""}${message.url!""}",
             <#if (message_index>0) >
             "picurl":"${contextPath!""}${message.smallPicurl!"/static/img/logo/ggec_logo_80.gif"}"
             <#else>
             "picurl":"${contextPath!""}${message.bigPicurl!"/static/img/logo/ggec_life_better.jpg"}"
             </#if>
         }
         <#if message_has_next>
         ,
         </#if>
         </#list>
         ]
    }
}