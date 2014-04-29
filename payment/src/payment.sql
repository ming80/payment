CREATE TABLE [payment] (
	[id] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[payer] [varchar] (100) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[_date] [datetime] NOT NULL ,
	[amount] [decimal](18, 2) NOT NULL ,
	CONSTRAINT [PK_payment] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
) ON [PRIMARY]
GO

CREATE TABLE [payment_license] (
	[payment_id] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[license] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	CONSTRAINT [PK_payment_license] PRIMARY KEY  CLUSTERED 
	(
		[payment_id],
		[license]
	)  ON [PRIMARY] 
) ON [PRIMARY]
GO



