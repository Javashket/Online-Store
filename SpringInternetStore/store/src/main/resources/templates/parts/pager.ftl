<#macro pager url page>
    <div class="col-lg-12">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Страницы</a>
            </li>
            <#if page.getTotalPages() == 0>
            <#list 0..page.getTotalPages() as p>
                <#if p  == page.getNumber()>
                    <li class="page-item active">
                        <a class="page-link" href="#" tabindex="-1">${p + 1}</a>
                    </li>
                    <#else>
                    <li class="page-item ">
                        <a class="page-link" href="${url}page=${p}" tabindex="-1">${p + 1}</a>
                    </li>
                </#if>
            </#list>
                <#else >
                    <#list 0..page.getTotalPages()-1 as p>
                        <#if p  == page.getNumber()>
                            <li class="page-item active">
                                <a class="page-link" href="#" tabindex="-1">${p + 1}</a>
                            </li>
                        <#else>
                            <li class="page-item ">
                                <a class="page-link" href="${url}page=${p}" tabindex="-1">${p + 1}</a>
                            </li>
                        </#if>
                    </#list>
            </#if>
        </ul>
    </div>
</#macro>