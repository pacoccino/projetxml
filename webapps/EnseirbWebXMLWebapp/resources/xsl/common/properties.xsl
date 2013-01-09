<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet 
          version="1.0"
          xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes"/>
<xsl:param name="expEncoder" />

<xsl:template match="/task"># <xsl:value-of select="$expEncoder" />
<xsl:text></xsl:text>
id=<xsl:value-of select="@id"/><xsl:text>
</xsl:text>title=<xsl:value-of select="@title"/><xsl:text>
</xsl:text>asker=<xsl:value-of select="asker/text()"/><xsl:text>
</xsl:text>owner=<xsl:value-of select="owner/text()"/><xsl:text>
</xsl:text>description=<xsl:value-of select="description/text()"/><xsl:text></xsl:text>
status=<xsl:if test="@done='true'">done</xsl:if><xsl:if test="@done='false'">todo</xsl:if></xsl:template>
</xsl:stylesheet>	
