<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet 
          version="1.0"
          xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes"/>
<xsl:template match="//task">
<xsl:value-of select="@id"/>;<xsl:value-of select="owner/text()"/>
<xsl:text>
</xsl:text>
</xsl:template>	
<xsl:template match="/tasks">taskId;owner
<xsl:apply-templates/>
</xsl:template>
</xsl:stylesheet>	
