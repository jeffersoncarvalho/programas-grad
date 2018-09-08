object FPrincipal: TFPrincipal
  Left = 263
  Top = 165
  Width = 369
  Height = 503
  AutoSize = True
  Caption = 'Redes'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  Menu = MainMenu1
  OldCreateOrder = False
  Position = poScreenCenter
  OnClose = FormClose
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 361
    Height = 297
    TabOrder = 0
    object Label1: TLabel
      Left = 10
      Top = 150
      Width = 64
      Height = 13
      Caption = 'End. Origem :'
    end
    object Label2: TLabel
      Left = 8
      Top = 176
      Width = 67
      Height = 13
      Caption = 'End. Destino :'
    end
    object lblMensagem: TLabel
      Left = 96
      Top = 248
      Width = 79
      Height = 20
      Caption = 'Pacote n'#186
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label3: TLabel
      Left = 8
      Top = 248
      Width = 87
      Height = 20
      Caption = 'Progresso:'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clRed
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object lbPer: TLabel
      Left = 320
      Top = 272
      Width = 22
      Height = 16
      Caption = '0%'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object ProgressBar1: TProgressBar
      Left = 8
      Top = 272
      Width = 305
      Height = 17
      Smooth = True
      TabOrder = 9
    end
    object FileListBox1: TFileListBox
      Left = 184
      Top = 32
      Width = 169
      Height = 89
      ItemHeight = 13
      Mask = '*.txt'
      TabOrder = 0
    end
    object DirectoryListBox1: TDirectoryListBox
      Left = 8
      Top = 32
      Width = 169
      Height = 89
      FileList = FileListBox1
      ItemHeight = 16
      TabOrder = 1
    end
    object DriveComboBox1: TDriveComboBox
      Left = 8
      Top = 8
      Width = 169
      Height = 19
      DirList = DirectoryListBox1
      TabOrder = 2
    end
    object FilterComboBox1: TFilterComboBox
      Left = 184
      Top = 8
      Width = 169
      Height = 21
      FileList = FileListBox1
      Filter = 'Arquivos Texto(*.txt)|*.txt|Todos os Arquivos (*.*)|*.*'
      TabOrder = 3
    end
    object bbtEnviar: TBitBtn
      Left = 232
      Top = 128
      Width = 75
      Height = 25
      Caption = 'Enviar'
      TabOrder = 4
      OnClick = bbtEnviarClick
    end
    object meEndOr: TMaskEdit
      Left = 81
      Top = 144
      Width = 88
      Height = 21
      EditMask = '999.999.999.999;1;_'
      MaxLength = 15
      TabOrder = 5
      Text = '192.168.165.255'
    end
    object meEndDest: TMaskEdit
      Left = 81
      Top = 168
      Width = 88
      Height = 21
      EditMask = '999.999.999.999;1;_'
      MaxLength = 15
      TabOrder = 6
      Text = '192.168.165.124'
    end
    object Button1: TButton
      Left = 232
      Top = 160
      Width = 75
      Height = 25
      Caption = 'Limpa Log'
      TabOrder = 7
      OnClick = Button1Click
    end
    object AtualizaIPs: TButton
      Left = 56
      Top = 200
      Width = 75
      Height = 25
      Caption = 'Atualiza IPs'
      TabOrder = 8
      OnClick = AtualizaIPsClick
    end
  end
  object Memo1: TMemo
    Left = 0
    Top = 296
    Width = 361
    Height = 153
    Lines.Strings = (
      '')
    ReadOnly = True
    ScrollBars = ssVertical
    TabOrder = 1
  end
  object VSSComm321: TVSSComm32
    CommPort = Com1
    BaudRate = __38400
    Parity = None
    DataBits = _8
    StopBits = _1
    OnReceiveData = VSSComm321ReceiveData
    Left = 328
    Top = 128
  end
  object MainMenu1: TMainMenu
    Left = 328
    Top = 160
    object Configuraes1: TMenuItem
      Caption = 'Menu'
      object Configuracao1: TMenuItem
        Caption = 'Configuracao'
        OnClick = Configuracao1Click
      end
    end
  end
  object SaveDialog1: TSaveDialog
    Left = 184
    Top = 160
  end
  object Timer1: TTimer
    Enabled = False
    Interval = 3000
    OnTimer = Timer1Timer
    Left = 184
    Top = 128
  end
  object Timer2: TTimer
    Enabled = False
    Interval = 8000
    OnTimer = Timer2Timer
    Left = 184
    Top = 192
  end
end
