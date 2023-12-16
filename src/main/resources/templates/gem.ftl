<#-- @ftlvariable name="gem" type="com.gruvedrift.models.Gemstone" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>
            ${gem.name}
        </h3>
        <p>
            ${gem.birthMonth}
        </p>
        <hr>
        <p>
            <a href="/gemstones/${gem.id}/edit">Edit gemstone</a>
        </p>
    </div>
</@layout.header>
