<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/books">
        <library>
            <xsl:for-each select="book">
                <volume>
                    <title><xsl:value-of select="name"/></title>
                    <writer><xsl:value-of select="author"/></writer>
                </volume>
            </xsl:for-each>
        </library>
    </xsl:template>
</xsl:stylesheet>
