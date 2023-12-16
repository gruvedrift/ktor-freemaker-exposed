<#-- @ftlvariable name="gem" type="com.gruvedrift.models.Gemstone" -->
<#-- @ftlvariable name="monthList" type="kotlin.collections.List<com.gruvedrift.models.MONTH>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Edit gemstone</h3>
        <form action="/gemstones/${gem.id}" method="post">
            <p>
                <input type="text" name="name" value="${gem.name}">
            </p>
            <p>
                <select name="month">
                    <#list monthList as month>
                        <option value="${month}">${month}</option>
                    </#list>
                </select>
            </p>
            <p>
                <input type="submit" name="_action" value="update">
            </p>
        </form>
    </div>
    <div>
        <form action="/gemstones/${gem.id}" method="post">
            <p>
                <input type="submit" name="_action" value="delete">
            </p>
        </form>
    </div>
</@layout.header>
