<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html"/>
<xsl:template match="/">

	<h1>Invoice Number : <xsl:value-of select="invoice/invoice_number"/> </h1>
	<h2>Invoice Date : <xsl:value-of select="invoice/invoice_date"/> </h2>
	
	<xsl:apply-templates select="invoice/item[quantity > 1]">
	<!-- Sorting instruction before applying the template -->
        <xsl:sort select="quantity" data-type="number" order="ascending"/>
		
	</xsl:apply-templates>
	
	<h2>Payment Info : <xsl:value-of select="invoice/payment_information"/> </h2>
	
	<br/>
	
	<xsl:element name="a">
		<xsl:attribute name="href">
			www.tudublin.ie
		</xsl:attribute>
		www.tudublin.ie
	</xsl:element>
	
</xsl:template>

<xsl:template match="item">
	<!-- <xsl:if test="quantity > 1"> -->
	<h3>Item: <xsl:value-of select="item_name"/> </h3>
		<p>Price: <xsl:value-of select="price"/> </p>
		<p>Quantity: <xsl:value-of select="quantity"/> </p>		
	<!-- </xsl:if> -->
</xsl:template>
</xsl:stylesheet>
