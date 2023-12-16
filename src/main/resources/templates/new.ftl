<#-- @ftlvariable name="monthList" type="kotlin.collections.List<com.gruvedrift.models.MONTH>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Create gemstone</h3>
        <form action="/gemstones" method="post">
            <p>
                <input type="text" name="gemstoneName">
            </p>
                <select name="month">
                    <#list monthList as month>
                        <option value="${month}">${month}</option>
                    </#list>
                </select>
            </p>
            <p>
                <input type="submit">
            </p>
        </form>
    </div>
</@layout.header>
