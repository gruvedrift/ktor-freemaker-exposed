<#-- @ftlvariable name="gemstones" type="kotlin.collections.List<com.gruvedrift.models.Gemstone>" -->
<#import "_layout.ftl" as layout/>
<@layout.header>
    <#list gemstones as gem>
        <div>
            <h3>
                <a href="/gemstones/${gem.id}">${gem.name}</a>
            </h3>
            <p>
                ${gem.birthMonth}
            </p>
        </div>
    </#list>
    <hr>
    <p>
        <a href="/gemstones/new">Create gemstone entry</a>
    </p>
</@layout.header>
