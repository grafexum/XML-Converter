<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" >
<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/">
CustomerID,CompanyName,ContactName,ContactTitle,Address,City,Email,PostalCode,Country,Phone,Fax
<xsl:for-each select="//Contact">
<xsl:value-of select="concat(CustomerID,',',CompanyName,',',ContactName,',',ContactTitle,',',Address,',',City,',',Email,',',PostalCode,',',Country,',',Phone,',',Fax,'&#xA;')"/>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>