<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
		
	<xsl:template match="//task">
		<!-- <xsl:choose>
			<xsl:when test="/tasks/task[@done='true']">
				<tr style="text-decoration:line-through">
			</xsl:when>
			<xsl:otherwise">
				<tr style="">
			</xsl:otherwise>
		</xsl:choose>-->
		<tr>
			<td><xsl:value-of select="@done" /></td>
			<td><xsl:value-of select="@id" /></td>
			<td><xsl:value-of select="@title" /></td>
			<td><xsl:value-of select="description" /></td>
			<td><xsl:value-of select="@deadline" /></td>
			<td><xsl:value-of select="@priority" /></td>
			<td><xsl:value-of select="owner" /></td>
			<td><xsl:value-of select="asker" /></td>
			<td><xsl:value-of select="@creationDate" /></td>
			<td>
				<xsl:element name="a">
					<xsl:attribute name="href">view?id=<xsl:value-of select="@id" /></xsl:attribute>
					<xsl:attribute name="target">_self</xsl:attribute>
					<xsl:attribute name="date-role">button</xsl:attribute>
					<xsl:attribute name="data-iconpos">notext</xsl:attribute>
					<xsl:attribute name="data-icon">search</xsl:attribute>
					Voir
				</xsl:element>
			</td>
			<td>
				<xsl:element name="a">
					<xsl:attribute name="href">modify?id=<xsl:value-of select="@id" /></xsl:attribute>
					<xsl:attribute name="target">_self</xsl:attribute>
					<xsl:attribute name="date-role">button</xsl:attribute>
					<xsl:attribute name="data-iconpos">notext</xsl:attribute>
					<xsl:attribute name="data-icon">gear</xsl:attribute>
					Modifier
				</xsl:element>
			</td>
		</tr>
	</xsl:template>
	
<xsl:template match="/">
	<html>
	  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta content="user-scalable=no,width=device-width" name="viewport" />
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0b2/jquery.mobile-1.0b2.min.css" />
	<script src="http://code.jquery.com/jquery-1.6.2.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.0b2/jquery.mobile-1.0b2.min.js"></script>
			<title>Liste des taches
		</title>
	  </head>
	  <body>
		<div data-role="header"><h1>								Toutes les taches 							</h1>
		  <a title="Retour" href="#" data-icon="back" data-iconpos="notext" data-rel="back">Retour</a>
		</div>
		<div data-role="content">
		  <!--
								tri par deadline (plus recent en 1er) puis par priorite (de la
								plus petite a la plus grande)
							-->
		  <table>
			<tr>
			  <th>Finie
			  </th>
			  <th>Id
			  </th>
			  <th>Titre
			  </th>
			  <th>Description
			  </th>
			  <th>Deadline
			  </th>
			  <th>Priorite
			  </th>
			  <th>Responsable
			  </th>
			  <th>Demandeur
			  </th>
			  <th>Date de creation
			  </th>
			  <th>Voir
			  </th>
			  <th>Modifier
			  </th>
			</tr>
			
			<xsl:apply-templates/>
			
		  </table>
		</div>

	  </body>
	</html>
</xsl:template>
</xsl:stylesheet>
