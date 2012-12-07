<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
		
	<xsl:template match="//user">
		<li>
		<xsl:element name="a">
			<xsl:attribute name="href">../task/list?ownerNameFilter=<xsl:value-of select="@name" /></xsl:attribute>
			<xsl:value-of select="@name" />
		</xsl:element>
		</li>
	</xsl:template>
	
<xsl:template match="/">
	<html>
	  <head>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta content="user-scalable=no,width=device-width" name="viewport" />
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0b2/jquery.mobile-1.0b2.min.css" />
	<script src="http://code.jquery.com/jquery-1.6.2.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.0b2/jquery.mobile-1.0b2.min.js"></script>
		<title>Liste des utilisateurs
		</title>
	  </head>
	  <body>
		<div data-role="header"><h1>						Utilisateurs 					</h1>
		  <a title="Retour" href="#" data-icon="back" data-iconpos="notext" data-rel="back">Retour</a>
		</div>
		<div data-role="content">
		  <!--
								tri par nom des utilisateurs
							-->
		  <ul data-role="listview">
			<xsl:apply-templates/>
		  </ul>
		</div>
	  </body>
	</html>
</xsl:template>
</xsl:stylesheet>
