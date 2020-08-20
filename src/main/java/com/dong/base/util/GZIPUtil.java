package com.dong.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author yangxin
 * @Title: GZIPUtil
 * @ProjectName roboqa
 * @Description: TODO
 * @date 2018/9/1115:00
 */
public class GZIPUtil {

  public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
  public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";


  public static byte[] compress(String str, String encoding) {
    if (str == null || str.length() == 0) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream gzip;
    try {
      gzip = new GZIPOutputStream(out);
      gzip.write(str.getBytes(encoding));
      gzip.close();
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return out.toByteArray();
  }

  public static byte[] compress(String str) throws IOException {
    return compress(str, GZIP_ENCODE_UTF_8);
  }

  public static byte[] uncompress(byte[] bytes) {
    if (bytes == null || bytes.length == 0) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
    try {
      GZIPInputStream ungzip = new GZIPInputStream(in);
      byte[] buffer = new byte[256];
      int n;
      while ((n = ungzip.read(buffer)) >= 0) {
        out.write(buffer, 0, n);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return out.toByteArray();
  }

  public static String uncompressToString(byte[] bytes, String encoding) {
    if (bytes == null || bytes.length == 0) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
    try {
      GZIPInputStream ungzip = new GZIPInputStream(in);
      byte[] buffer = new byte[256];
      int n;
      while ((n = ungzip.read(buffer)) >= 0) {
        out.write(buffer, 0, n);
      }
      return out.toString(encoding);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String uncompressToString(byte[] bytes) {
    return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
  }

  public static void main(String[] args) throws IOException {
    //String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    //最终得出结论的报文，字符串长度：4937，压缩后：：3541，压缩率：71.72%
//    String s = "eyJoZWFkZXIiOnsic2lnbmF0dXJlIjoiRjA4ZVpRbVpkUE4yaktSOVpLWU9WWWo5TS94V1NjQmlF\n"
//        + "WXFnakxvRUR4RUVrL1ZNV0t2dURScEJVQWwrc0h4WVRlaVRIQXpTMExXalxyXG5BV3EvZ3dSY1Ey\n"
//        + "VVBXSERhalVlOFZ2YTI3YkRCMFVtOXh5d2F4c1pCc3J5WVRBNG1FazhLdHlYOW9tNnlFMWNwT0E3\n"
//        + "NlhQM3NSTDI1XHJcbnNMU056NVZOOWhhWHJpamtGMUE9XHJcbiIsImNhbGxfaWQiOiJKNTRxQTZz\n"
//        + "UUxJSVBDUGFoMHN4UUhSVWtSNDNsTzNPRGo1ekhGQ1lxdDN2UGhMUU83YjJEbStLT3dnbkprZksr\n"
//        + "NS9YVDM4Q2tsRS9FXHJcbkRQeGNxRFpZQWhIZ0pOb2xQVzZJQzdlSWdUcncyeEhjRXpLYVIzZE9J\n"
//        + "OE1LeVB5T2hVMFhDVUUwejZPZkxITVpudERoMW1Zb2xjc29cclxuUlJab25ZREtRL29QLytCb2hu\n"
//        + "ST1cclxuIn0sImJvZHkiOiJLWHlFcVBFZFVaQ2txVEwzRmx2S0dOM3NaUG5sVkh3YmFKMGJMclI0\n"
//        + "bFRZRlpjeXdPVzhkWlI5UGVIOTk4bGZxTW1ZcHFtdnY5V1A4XHJcbkIyQUVlenVFMHNQdkhyaDk2\n"
//        + "U1NGWUFLTkFGYThWMmhkbUVBTyt0NnMyaW41eTdFU3JVdXpiNWxpTytvcXM2STcwQnhQcGpmekVS\n"
//        + "VFlcclxuT09qbTVweXo5cko4RldxRW5JbXZTY2o5TUlRWjNVNE9YZUUrblgrV2UrQmRidE9oSm9T\n"
//        + "aUxqT3piakRlbjhmT2I3R1NiOVgzelZTbFxyXG5kbW5DNGJZdzBrT0QvYlNZdjZhWS9mOTh6Z0Jp\n"
//        + "ZGE4RUlOOWxLcjlneFlsYXMxcFNNOC9teHZYVmZ5UzFrQmNqVUJqb1BNS1V6WisyXHJcblBnMm1u\n"
//        + "cERnTUJXT0tFMlZxRStvSUE5RkJsZjJqS2FiRFBWZGRZWC9iam15THVpR3laVGJyMlpma2hVckxE\n"
//        + "NHBheUt6SDNkRGVUaGVcclxueXJoZGhqb09BUS9oSE9YakJyVm9uUU45b3o5clRhVnliWlRYRE90\n"
//        + "MWp3RDlkUkhLUmFYcUNKa1BZeTZ3UjhzcTBGdGxmMzE0VjNWcVxyXG5OUk9CUWJBUnUvRGNmU0dY\n"
//        + "aUM0Y0xqL3g1TzliU202RFdLN3JTaW8zQ0lNMGwrSlpDc3RnT1hvRkhBdEFDL2FSU0FrY0lRL2h1\n"
//        + "amRWXHJcbnE3N2NYK1Z2Uyt1WWtFSTU5d28xYTVsU2lJK3BUTmNBNlFJTzhGcm5CVjIwNnBMbThF\n"
//        + "N2xNUFEwYzV3NWVEclNScDFEbXhaZHhGbklcclxuUk1RRWtVUkFEcmxMSEU1L2JGeWxqWHNRZklp\n"
//        + "anUwVnhUSGU5RTAxM0ZDMUt5ZVRSdDVxK05Kc3VuTUdTSlpnWFFKL2JxMHhDU01xdFxyXG54M1pL\n"
//        + "SUE5MkJ0YjhqZGlVaUtBMitJcGRya0lEL1VtaFRtTGhza1Aya2JBS2t3OFVnMmRlOXM3U1lXdFhW\n"
//        + "SkRCR25sZ3hidmcwS3gvXHJcbndHSUZDRjVQMWV0Z0xMOWs2WnpHMDhGSDhZSXRiZ0J1RnpvNTRh\n"
//        + "d2tBVjBTYzViVnhEME9zZmJpamVadCtOeWdpMzhUL3FBQlVzd0JcclxuY2NJRGtDVFlONGFXRmxn\n"
//        + "Z2pRT1ZXcUp6cGdqaFd2ZHBKZDlaWnQ4YXlPWWs4b3hwVzgwcmgxbEJGSFRVZTRMRnZmcTlSOUNS\n"
//        + "TmpnRlxyXG5tNGhpSE1VTXI0YnhsWEV2bDFMQzNJREU2YTRNTHFTL1NRNlRYdTg3U0N5dWlQeGRF\n"
//        + "Ky83T1VrT0NGek9xN0VWekZGU2wrbCs5NWxCXHJcbklHeERrb092ZGxzU01pUXFqak9FN3BScDM5\n"
//        + "L1B4bW41R3lqaE9ZVWNYMGNlVTg5QzdnSUVKYzhOR1huSFJ3aHVwRUxBZjRrSGM0b3BcclxuWFh3\n"
//        + "dFlYWXVPelZzS1pEc2xPbTVvQlVPZVdlNlk4eXMvR3BBOUFxNk5jQXVGWmhSY1o5L1B5SmVNT2lZ\n"
//        + "VWhEWXFKWmJHd3YxVjA2cFxyXG51MFRpM3ZYNXV5Q05KaHJ2dW13Y1lZbEg3ZTZsSzAzeENqbzR0\n"
//        + "aGh5NHIveFBhU3piQ3lMVEloRHJJNU4vRmZHSXNhZ05LT0V0M09YXHJcblY2V0I5NVkza0kwZXdG\n"
//        + "QnRwUXQ0R25OUjJRQVFQSk1WVVZna0IvRUhWTHBxUmlmaTdyeEJ1bWgvUVMxM2ZtMmVxM1QvdjBZ\n"
//        + "WS9EZUhcclxuME5NanlTT2tRNzdyZVZhaFBlRFVyRmN6WWZJN3FoWmZUMWJqS296dTNmZjl1UHFT\n"
//        + "aFNpNk5WcjJyNFphVG4wVURLY24yZUh5angwQlxyXG5rc0ZqM3NtQ3NaZW1GUGJhclFOZWpydUsr\n"
//        + "Y09nUFB4NkkzZmszWUlReGJ0SWI3SjFHZ0pMcG5YK1NVVUN0ZmROc2FBOW52SklKTy9qXHJcbmc2\n"
//        + "bnBSSWd5Yk9aV3BPK2NhaDRwOHk3Y1ZDU1l0c1IyUTFDbGVNOFU5aEtodDI1c2cydkRQTloyYTdF\n"
//        + "YXJsYitVZUJsWVhhbjhsT3hcclxuL0ViRHIyTG1ySGFGSTdiWEJndXNQS0hTek5LRCtiY0phZGtL\n"
//        + "V2hCSktGMS9MQlBVTlBtMmZScGJ5NWVCVllzcitPUzUwNFBrMld2cFxyXG42SWdmU09Va2w5ZDlw\n"
//        + "ZWJMbDFsYUIrRmFybHVucThNUElKekN0TTI4ekdLYjl1dElUR1JRVmxINFdOck9OaGhNMDl5MTY3\n"
//        + "UlVndjIxXHJcbnNTeXN5STB6clJLR0V3VjVBZ3dWdDk2d2tZMllxcXZSeWNicUhFME10VTRYcHkw\n"
//        + "OUtpNGF1SzJSRmhYQnF6eFc3ZnlMREtpdUFQQVpcclxuUzh6UldKWVRTRm9BalNSUWo5b2RtcXdF\n"
//        + "Mjk0SFZhWGZhaXdXaEpITkVwY2tsS1M5eSszWFNMYitSWC9VcWtNOVBXMWQzRnJ3d1BZc1xyXG5W\n"
//        + "VlZWRUg0aHJGRmlNMUQwTFpBcUJJQXNNQitZcDN3S042dGw5cyszSW5ERDBOZERpY1VQYjRienpG\n"
//        + "MUk0VjVkR1ozYXNDSUJLazBtXHJcblVkR0FWQmhRMW1wL0JYL1gvSDhmeWNzNlU0QUNMNTBleDBE\n"
//        + "ckpPRmREY3pSV0pZVFNGb0FqU1JRajlvZG1xekRiTzdsK2Z5N1NCUklcclxuTEo3L3dqcjJJMjJm\n"
//        + "U0xKaEpnUWJPOVZHS3VKL0hYMnlNTStqeTZVNmJubkxkVWY4RGJDSlhEOGlwakwrOU0yRzB3UnRj\n"
//        + "OC9TeVpjVlxyXG52dlo4ZXdVVkY5ZTN1WVFGc0ZrYnZNVWF5ajViVXNBeWNFSUU3algzbkNLdElE\n"
//        + "LzIzZmRvKzZQU0VCVWJ6RmVJSFdOV29WR1k3eVF6XHJcbmFnaHo4dVJ5T3J0UitxV0VkTDNpYWxQ\n"
//        + "UE11WC92N1JGa1VEUkFCS3BkT3BSUmpxZ2hvYy83L2I3MW1LM2J1YjF3UDZwOVIvZFVUN1Zcclxu\n"
//        + "dmxOY2tLamxDeEZHY2N1SUdqOHpGWUFHUWVPNEVkNTRGRXB2Yy9wTGhFc1VnK2lGaGY5WXl5S3hQ\n"
//        + "OUNRdnFISWdRb3lySDlzbm1ZRlxyXG5hbWJ2QmU0elFYYTBZYjlSMXU4RlpraXRJRHZ6ZXptOTZW\n"
//        + "bGFQZ0NJekxqWE1GYUdEQWJmTTEweDNZdDl6ZExzenZWMGRnc2YwWUZJXHJcbnZiYlVkWVlla3hl\n"
//        + "d0psSFJnRlFZVU5acWZ3Vi8xL3gvSDl5TDh6cTJ1aDQ3a2M3SDIxN0VmSFF2UEZ4bW1zZ2N3Nk91\n"
//        + "dXRRNHFZcExcclxuREYxSU1MdkszeUxMTTdvcHJWZUVYQ0xEc3Jsajh5YlB3ZkdmN0dBV1MweDhH\n"
//        + "U294U3ZBczVGOTUrbk5Vc3VhVU9uZlRRVWxabUNGeFxyXG50RGpnTk82elJra04vaExNNmNvdjJ6\n"
//        + "K2Zlc2tJa25IYTlXNHgxRXdBQy9qdFBRbnl4MjVFZkJBLzJPdXJYTFl5dU9xUXRZOUQrVldZXHJc\n"
//        + "bmFjT2l4MklYMjFieWdUYmVUOE54TUNlKzBrdk1jems1QkJzSW1TdG5pNmVGQWtWQVp5a3VJZTBC\n"
//        + "UjFsRlphd0NVOXlPLzNldkQzVnFcclxubkNTR0lrYVdzaDRiYUQwRlE0cGhWT0dGL1NzMXBWcTRV\n"
//        + "WERSamtvRVhtcVJOdDRIeHh2aEdjSXJFalJxTUZWeDVsc2U5OFVZWXJRRlxyXG5HblAzaHVaSkN2\n"
//        + "SHpmV1hxeFVkQTFWZFphKzR1OXQ2Zmt1VnlVWk1HV1JFL25QWG9NRUdWZXo0UkgxdVl1TGgybUpT\n"
//        + "K1oyZWNaUzVsXHJcbmtweHpiWEFsVjlocU55bVRrNmtoUXVXalB4Q1NpN2pDVUcwUzFIUGpsUjF2\n"
//        + "Rmp3UWZmaE0vRmxRbmZBaGRhbTI5YmFUMVRZZkhxamxcclxuQXdTNTFoYWdyNmNRMUdkZnRJTGgx\n"
//        + "Nms5dmhsYTE0VmpId3k1aUlxY0RFRVB6dXVPcS9lR3FmcTdxK29wWGFWYTE3eitaTkJCeXRuc1xy\n"
//        + "XG5HeTE0ejI1U202TFkwMDY0MENDd1A3SDVCdWpJRjJSak1YZWhYS2FFL1lTUHkzSm84Qk56V0xC\n"
//        + "S1A1ZEhHWHQ3alFtbnBWUHhybkMvXHJcbnYxd3ZZODJKaTcrMTRqaUxJSGZhbDVqTm1zMnlBMDNz\n"
//        + "XHJcbiJ9\n";

    //返回疾病列表的报文，字符串长度：22865，压缩后：：15304，压缩率：66.93%
//    String s = "eyJoZWFkZXIiOnsic2lnbmF0dXJlIjoiWlUyU3lzdnRIVzJKZWtUS3ZTVW5kRXAraWl4bWJyNWpk\n"
//        + "Wjd3UWQwRjhZTVExYXdTVnNERWlqc2VLU1FPd1Y2OVIyS2VBZXo4UjBoWlxyXG43dHNDVU00a2Mw\n"
//        + "VmFLU2g3TDU4U3g5Qms2ZSs4RTlDTzRIeUV0V3JZdk9OTitLWDRsNnhUWnp1SmxSakdRdUliamU2\n"
//        + "VmRGRW1yNlVoXHJcblhwQk5abEt4NEpiQXFvR3FHV0k9XHJcbiIsImNhbGxfaWQiOiJaREdSc3d3\n"
//        + "c3VTZlozd1Nadk4vRnVNbFdYQkdSZE10RW1BcGlVbjhaZldYcXgwQVM4MWZYbk5FVk0zNFZVZUNM\n"
//        + "Y1RzYjZMWXBnaWVLXHJcbjlxTy9BNTM0TWFDV1Q4TFM4YzJvb0RPOVN3Q0p4bGk3bUpUWk9yRk1H\n"
//        + "U0J6S2ZXbERvNGFjK1FtRFYwTGRTYklKYlNvTSsyWG9HK2dcclxuVk9UWnFKY1VWbkNvRG1GaFhy\n"
//        + "cz1cclxuIn0sImJvZHkiOiJLWHlFcVBFZFVaQ2txVEwzRmx2S0dCb0NMU1l4Y1hVbUZIbkpGMkVj\n"
//        + "R1dxMkw2L1VDbHVnVUVKQjB4d0pvMFZ6blVRSTBxVTlKeG5hXHJcblpaRU8vQUJRM2N6K1hab2N5\n"
//        + "RVFUYnpIT0x6N0YyS3BFMGpqUkJLNHN2dDJjTU90RDhKejlidG1XNUp2a3IyUnFlbjZwNGNNVzhK\n"
//        + "MWFcclxud1J6ckNnZ3kwVHlyRlNicjVFNDhFMGhmbGgyeHgwRWpGeVIyS3pNYWdnUDBNbjdCZDU2\n"
//        + "NTdMVnppLzdhSFp3L1ljNzlIZTN2UitiTFxyXG5ObmJwb1NCeDA3YnJZcjhnQTNidysvSWlOSlVF\n"
//        + "amZxMi82MTBCOVRWTTJMcjdPcklSTUtBbjAxTVRwWDgxUHVxdi9zM2hJVXdKWGtnXHJcbkt1OWk2\n"
//        + "dnJKamM4N3NyaVdPR2VQY3hhTEpvTXI1REhoTVFrQ3U5c0VBZDE1Zzd5TTR5UTBIY3M4OW9WTHht\n"
//        + "VnNsSVdJMm5zbzVnUTlcclxua1Npd0RDSU1ZMFRMaFFkSTVqNDFiNHptMjZzNHFnSlg3UmY0VDZj\n"
//        + "OE5CN21yNkhvQkgrYTExOXkyZkdqQzUyaWdLb1FUNWI0dDY4N1xyXG5Pblp1MDBETngyZVZUdDhL\n"
//        + "dWVtNTNuTjhQTGR3cGUwZ0RtS0EzK25Bd3dob0RlcDg2TFgzc3pjOUxTbkZTd2V1TmpXME5IcjBn\n"
//        + "N1J6XHJcbk9SVmdHcHpVUlJLWE9aUko0d0NWa1hpcVp1MmlYSXBxNnNRNC9aSTBxaS8vR3FzV2wz\n"
//        + "S1VNTDBGQUpQY29yV01Bc0VLY1pxUWVFVWdcclxuS0lMdmtvRVRkTFVFNTgyajBUczNicDhiK25M\n"
//        + "dkNzTTRESWFrODBiSkwySzFQS1dDR0V6TmtMOTJVQ0JhZVdLcE5sQUJPMGhDVnF2NFxyXG40dC9k\n"
//        + "cHhpQ3EveHZ6YjJuS0VTZnJ5eDQ3cThLOVBZQmRKNGFqNUYvencwenF0UnhBNDVJZXpSUnJDazhu\n"
//        + "dHRlSVhHR25YQmNNcHViXHJcbmlzenpuL1NIcUZJaTdRQU5EL1hmM2Z5aCtqZmlWZzFhZGtJbEdp\n"
//        + "WjYrVmJDVUNrZUNSWmYwR0RyYjAxV0Y0aGlmWHVnVnd2QW5wQXZcclxuMnpnczN5WlJYbCtYUzhl\n"
//        + "VzcrczJVcHQ0eXRsWkhnZXhVZmxuUWJGZUdLcUpuZ3FmNmEzUkZEZnNxUGpNZlMxc29aMytBZWhT\n"
//        + "Uyt3YVxyXG5odDI0cEVJMzdFbU05YitzQWFMRDlFdS90MWNWcUViY2RNOTQxRlBPelRMWnpxbTRS\n"
//        + "SXB5UXVpanBBMm9VWkFZaGZBQU5rRXR5dTE5XHJcbkVGbWUwaEhITVVGWDd2UVBpMTN5MFBZWG1h\n"
//        + "dUMvSUNNSnNqMjlOWTkwUGg0WCtTR2U3ZmtZTWlZZ1VxNFEzbnd1NFlvTDZkV050VUhcclxuZkxv\n"
//        + "T2x2SDFZSmlTajNDVmJCVGFVV2RZRUF0eWV1cm1QSnBSUEN2Y2ZpSVpiWDRwY3hLRzFyZXpDVitn\n"
//        + "amlSKzF6M052N2VTbVluSFxyXG5rdy9xUVpEVWNzV1lYTGtQTURIRlBLZUNjZzBySFpESkJKN2Nu\n"
//        + "K0kyZi8vQXRHcXNWWDJmTFNEVmVnWmJXT3FheExxWFgzQ3ZPbFFVXHJcbkNwS3Q3MzVqYVFQNjhs\n"
//        + "Q0NiZCthNElJNlNadjhkNDBYSU4ydVZFL2tZbmQyMEw5a0grZnNEejUxSkJMMVNwN25TODdMOG9r\n"
//        + "cDRjek9cclxuSFZJWFp6TEVQdENPQjFqSkRmdDE2UHBncXNCcDN0TFZpUjQyenliMmMyTjR6Zm1K\n"
//        + "TWsxL0RraWRQWEpEczJGZGdtRGpCNE9zMGNIclxyXG5Wb1VuR3RVTXZVbHpmd2dkckVhM1E2eUg5\n"
//        + "ZkZWUGlYQ0FRamNmcUhHcGt3K051M1ZZWStBWHJKY3ZLMXhQVldQanB5eE5vWlM1a3VOXHJcbmxS\n"
//        + "Mit2K2FGS0hWbmtDQ0VmeFpvWDhONzg4OElVSjhvTUZwSEpPZ1doR09HK09FTlF3bWQ4czk4Wm5O\n"
//        + "VnFNYVFjM3oxYUN2VmFWNlFcclxuSmVVTE1jV2p2bXJaSmZLbURraWRQWEpEczJGZGdtRGpCNE9z\n"
//        + "MGNIclZvVW5HdFVNdlVsemZ3Z2RyRWEycWVvZVoyYzFEOGVaOHhTOFxyXG5uTlhvakgrT3dqUjFh\n"
//        + "NHdmVTZwcUVuVGI0VENGY0doOUFZNWdUdEJ2S1BBNHhCUlZHK3ZsUU55OFE0UGVsQXpnMU9rNlFu\n"
//        + "MlpoZ3R0XHJcbm84ODkvQzBhS1lOK1cxRDk3QXk1L0JCdFROaURxQWZ0bXgydndJYnpqRGF0c1Ja\n"
//        + "WmVGQ29jYkJ4U3JTYW5OU2d0eVRBSEVDZXg2RXFcclxuSjFCK05MMFpDMkIyME1VVUdVenFBckFL\n"
//        + "VXlxcWV0cENTRVZZMTc3WGxkU2dpMEpFSVBndHcrb1RZQzhwaEx3a2FIOVEwWENhS2tETVxyXG43\n"
//        + "SDJBTVo5QS9vdXpvNFFjOGZhVEpodkNXUkFhZUNWK1hEQmVWQzdSRnRGVFNjYnNwMGMya1pFKzY4\n"
//        + "Y2VyT3FFNkJMd2w5c3BBeUNwXHJcblpuWWlSRy9LV3pMSVprOWxCMHd2ekh6VzFVVDFZSy9DY1J1\n"
//        + "c281UFFIcXRJM0tXeS9BUWdqZnlUVWVRR25lNGI3TXN1bzNtbnZDQitcclxuWndFa3dVTjA3T044\n"
//        + "WjBBNHRjWi93MzFvOVVoRFIwc1VsMUcyWkV0Ty9vSGdsQmtPamw2RHgxZGwxSVZ4U1BodzIwZExx\n"
//        + "ZmRIdEllVFxyXG5aVk8xa1hnbThZVm9HM29sMW5lM1F6eHk0c3VVamE4eTZJSGFGbHpta29jTlEz\n"
//        + "c1FKSnlMQmNzVWRMdE50QzVlekx6VmlEczZ0bGdqXHJcbkc4K1FtY3d6eGE3OEVFZkV4OTlTYjVh\n"
//        + "VldRSnFtTzdHSzlsMTV0SFdnOS9Ua08vazZzMnFha1ZMV3BvdGo5d1BDSVBRaDJ4SkVFU3hcclxu\n"
//        + "eDZOUTRQTzJzVWRTZWlrT1NKMDlja096WVYyQ1lPTUhnNnpSd2V0V2hTY2ExUXk5U1hOL0NCMnNS\n"
//        + "cElRNmpiOXhKbXg4WDNPUUdLWFxyXG44M3NDcW9RTzVkMGhEVlY5TW0zQWw0U2EzVlJqaG83ODJH\n"
//        + "dEl0N3ptbTRYbkVLNnpJTWIwSjdlL0xETm1HQmxPK01zbmd2cDhUbUVxXHJcbm81RWlGcS84bkF1\n"
//        + "TisvKzVGRlBxWmZlQUlYZ3k1VGxTbTFick5Dd2FCTDRQV3hXU0crQlByWW54Lzlva1pHN2ZIK01N\n"
//        + "TTVYQUh1cC9cclxueVlIWHBiM0ExK1VaenpIWEx1SVIxVkRBNzI4ZGpzOC9FRjdld0Qxc2YxSXlS\n"
//        + "cFJBb0pRN01MNUxPdGxLRzE2ZXBGRElvaXlHcGxvMlxyXG5TSExuN2gxa1pKcU11cFF1SUxoaUpq\n"
//        + "Z2JLSWJWRkRXODk4MVV2bE9zckxVRjBQSmNRWHJ6ZlBDWHREK3NTUGFrNUdHWjBadkNiSUNnXHJc\n"
//        + "bkNzSDY5NG5naVFwOXVyYXFJWUdOUmhHbW9RZ0pWMG1nbzR0Ny9yVUVqWUFRWGNMelBYNGtGUU5T\n"
//        + "T000dU9pZDVFZ0d1ZzZMb0Q2eEdcclxucEtWODFGdUpaNE54MFR3allhQ25GL3BSWllPeTNIcDZT\n"
//        + "dkFzVVBQbWI0RXcrMFpZY2h6NUhJTHVlSWdVcnRXYXg0ejluU3VHUXRQM1xyXG41TzFCKzVkWHE3\n"
//        + "UjFyMXpYYXZra2ZDWlNvdmdzMmNldTlkTm9RcC9wNWhuWko4MnpzWkg1aTZEWVVOQWtKSG5jL2NO\n"
//        + "MGx4K0syZ055XHJcbmJjcStyc3ltY3FuV3dsOVNqMGIvb1UyK0d4UktMaWp1VzE4aVl3Z0hIbnZQ\n"
//        + "K2thdXN5REc5Q2Uzdnl3elpoZ1pUdmpMcHJpbnFKNlBcclxua3FybE1Ob2FWRnJ4aytVQnhBaG9q\n"
//        + "NndMNXNRS25ISDgwK041NC9hNWIxRGp2ZnFualgrOFlHQkZrNDZYUGMxRkJSNnFzWTFUc3hxYVxy\n"
//        + "XG5SMVM2ancySWIwM3JsbFQxZ2R3aW01Q2pBc2t4NEhCa092dHJGM1RtNXg3ODZya1poZG4yZ0Ir\n"
//        + "bWZXS1Vxbks1cm1pWDZQSkNtSE1CXHJcbmRHOFdIcTArMjErN1RhbVVYQWJ6Rk9jTVZob1VoOXZL\n"
//        + "aXd2ekVDS0VIaTVFSjdtUCtZTTdkY1RtNTRMNHAvYUd4MHEvdmlTNlc0R3Zcclxud0liempEYXRz\n"
//        + "UlpaZUZDb2NiQnhTclNhbk5TZ3R5VEFIRUNleDZFcUp6dzFpZjV2bEE5dytpeitDb1Z4M3AyeGdF\n"
//        + "ZFBIZHg3WlozdlxyXG5RWVh5N1AvakRraWRQWEpEczJGZGdtRGpCNE9zMGNIclZvVW5HdFVNdlVs\n"
//        + "emZ3Z2RyRWIwcjQ1eWhKUE5KT1hwZ2NRL005MXFLeVFTXHJcbjcraXZ1UnBSeTFOU1pKSHdYSUNn\n"
//        + "Q3NINjk0bmdpUXA5dXJhcUlZR05SaEdtb1FnSlYwbWdvNHQ3L3JVRWwrYlpNVmVHZW5ITmJudytc\n"
//        + "clxubWNJUytjQnZWVkRUMDBUYnVYQm5hdVROU1AydndJYnpqRGF0c1JaWmVGQ29jYkJ4U3JTYW5O\n"
//        + "U2d0eVRBSEVDZXg2RXFKeWo1N2JkVVxyXG40VkU2aS9kd0NjS1hQVVhkSXpudjQ0WnNVZ3NWT3pI\n"
//        + "M2xsTGdEa2lkUFhKRHMyRmRnbURqQjRPczBjSHJWb1VuR3RVTXZVbHpmd2dkXHJcbnJFYTlqZUwv\n"
//        + "Q1dmMGptM0RxeDhjdGN0N21LZkJQWVZpVzM2eTZvTGZuNXY4T1JZWTVXT3IzeTBEb2daZTNCV1J5\n"
//        + "Qkt1c3lERzlDZTNcclxudnl3elpoZ1pUdmpMdjdWZUdpR1VKRDRxaEVmMGRTazF6QzVtMWVLbTQ4\n"
//        + "bEZISENxNmYxMG1UQkhVd2RoRTBhOXlRN3paS1p0R2hvY1xyXG5rNTUyd21jd1ZCRmI0eFlGdFU5\n"
//        + "UFYvLzNjbHJacXZSK1FKVHBrRzdPaTdvbU9GM3ViR0YwbDRQS1VTTkhhc1l5cTBqY3BiTDhCQ0NO\n"
//        + "XHJcbi9KTlI1QWFkN2h2c3l5NmplYWU4SUg1bkFTVEJRM1RzNDN4blFEaTF4bi9EZldqMVNFTkhT\n"
//        + "eFNYVWJaa1MwNytnZUNVR1E2T1hvUEhcclxuVjJYVWhYRkkrSERiUjB1cDkwZTBoNU5sVTdXUmVD\n"
//        + "YnhoV2diZWlYV0FNN052emgxVzBnNzFVMkp1Q09kcmErUURLb3BlQUpCNVlSVlxyXG5XWXZheUd0\n"
//        + "RVBBcmxIUmp0L3hnQXhvOHJUU2ZlcjhDRzg0dzJyYkVXV1hoUXFIR3djVXEwbXB6VW9MY2t3QnhB\n"
//        + "bnNlaEtpY1B0MkJoXHJcbjZ6VlVZWlhnOG9GdWFvSTZqYVhXV3JWczFlZHJuTEZqRG9HZFZVWHBt\n"
//        + "OEc0WTF4dTVrZTFDUk1nU1Z0VjRIUXlBN2lKODBVTE95Y0VcclxuN1BtcXBvTEcvOHltYWJxYnY0\n"
//        + "V0hGTUMvUmdHTTlSdmFVbXFDOUExcHM5bzZWenVZY3JGR2hXa3FhMnpEN2tlTTQzaWVyOENHODR3\n"
//        + "MlxyXG5yYkVXV1hoUXFIR3djVXEwbXB6VW9MY2t3QnhBbnNlaEtpZjRtMjFOSml1cVA2blVscEtE\n"
//        + "eTZIQS92dm55cDJsbE16QjhkdG9EQ2VoXHJcbmxzL25sdVJ1dW9CcVZ5M2lnRFo1ZlJHZTNKL2lO\n"
//        + "bi8vd0xScXJGVjlueTBnckZqdjgzbWd5dFlaNjJiT2Q5aDhSMVBsQVd5UCtOaDFcclxuOGo3Q0sr\n"
//        + "b20zcVVOVnFUV1BIMG82THp2TlljbkZKakVVUjVXUmxvNVZDd1M2aTZSclpqVW9neDRzY0NFbm5i\n"
//        + "ejVaMjlsMmlKbitrc1xyXG4xVDNCVHhyaDAzU2NkdGlObXdJeHJyTWd4dlFudDc4c00yWVlHVTc0\n"
//        + "eTRad1RhNGhFdUpqTzJhS1ZTc2pRaVJGalUwR1JjbkttREdQXHJcbk9oSS83ZTJrVHYvMlNUNy8x\n"
//        + "WEttcFhnb21taDJmdzVJblQxeVE3TmhYWUpnNHdlRHJOSEI2MWFGSnhyVkRMMUpjMzhJSGF4R2pI\n"
//        + "YW5cclxub2c2eWxpd3pJT2l2WGJGRkd3UWFaYkZ3VHFWQThJY0JhYUFGNHJBd2hYQm9mUUdPWUU3\n"
//        + "UWJ5andPTVFVVlJ2cjVVRGN2RU9EM3BRTVxyXG40TlRwT3VTODNZUUpkemtSaUFQdEQzYm5qRld2\n"
//        + "M1pQZlRCeXU0Zm1QY0VCVkswTnhCNnRuejN6MzFteDE5dFJFZjdYb2M1RSs2OGNlXHJcbnJPcUU2\n"
//        + "Qkx3bDlzcEF5Q05scmlCV0g4Vzl4QVJya3NlQksxUjJnRkhsRFJXdEhqVnE5U2tZU3ltM1ltRGdk\n"
//        + "SVczb0RKUzVoUndqcnNcclxuR0hERm1nK3l4Sk5qbFc3RzJQdktLNWVFQm92OC9MNloybEpBclNR\n"
//        + "a2R3MFIwb2ZNRGpSOGk5bzdwRFBrTndxODU4NTBlR2hFTUNkNVxyXG5VSzlEQjduOGNZN3luK25t\n"
//        + "R2RrbnpiT3hrZm1Mb05oUTBBQU1QWlYreitZdGd4U1NpUjNwUlRsTGZ1WDY5MVNyL2Z2Q0E4L2M2\n"
//        + "SG4yXHJcbm00S0ZvSmJkUWtSTWk0WXNub1BGUFlPbWpXemM4RlNHNkxEWjNYQTl2K05MenN2eWlT\n"
//        + "bmh6TTRkVWhkbk1zUSsySEdQdmtZN2JVZ1hcclxuTnJJcFhZclZXUVlZV3ArK08wNDhIbE5BYUFU\n"
//        + "YklXazF3RUkxRm9KUkdlaUgyWWcybk9QMlZlQjBNZ080aWZORkN6c25CT3o1cXAvVFxyXG5zRVRQ\n"
//        + "bXJyQ2pvajV2VWJKZlZNQmpQVWIybEpxZ3ZRTmFiUGFPbGM3RWRtcE9kWEZSVnZVbktXYXJJd3c1\n"
//        + "NUkrNFJUOFlHbno5dHREXHJcbkMxOXBVVlZMenN2eWlTbmh6TTRkVWhkbk1zUStLQkF6Wis5Q2Fx\n"
//        + "dUJVcHpqa1Q2aHpXd3VxL05YaUdkeitnVE41VUR1TUJIdXNSaWFcclxuL1lXc1Y3SVMyZk1tT05l\n"
//        + "a01JVndhSDBCam1CTzBHOG84RGpFRkZVYjYrVkEzTHhEZzk2VURPRFU2VHJjeC9yUCtHVGZLeTNX\n"
//        + "VDlhb1xyXG5Jdno5bnBoTjB1L0x1NnFVTGpxUW9rL25FSkkrNFJUOFlHbno5dHREQzE5cFVWVkx6\n"
//        + "c3Z5aVNuaHpNNGRVaGRuTXNRK3VEa2tycGFxXHJcblR0UkhjZXVUQVNVUGdPNVQweFFnRmFaU1VZ\n"
//        + "U0xWd0xnemx0OFN3YW9ES2ZQMUFGa1g0RE5wWXgrTUlWd2FIMEJqbUJPMEc4bzhEakVcclxuRkZV\n"
//        + "YjYrVkEzTHhEZzk2VURPRFU2VHF6TS84Q2FrbGNaVTY1dGVZTXRnUW4vT2lGSE0xU2IwV0ZzY1F1\n"
//        + "VmZIVEhhL0Fodk9NTnEyeFxyXG5GbGw0VUtoeHNIRkt0SnFjMUtDM0pNQWNRSjdIb1NvbkM1TWFy\n"
//        + "cGpxaGhhdDJ5VXlFWmpoWXowS0U1eU1KMDduWVQ0QU9zT2hQNVpxXHJcbjB2eXF3OG84ZnJmYWli\n"
//        + "UCt1UnlGSEgxYnpheVliUEpLallUdDBueGNjYzBxZkI5czlnU09GUWNJaWx5UTNKWHBKQnBIbWtB\n"
//        + "SkFoK3RcclxuR3ByOXB5RE9uK25tR2RrbnpiT3hrZm1Mb05oUTBER1JGbmN4Y3BMU1JxUjRhQlBp\n"
//        + "NUxjRDVoMWJwUlVCdzdBU3VTTjFTdm0vL2tWcFxyXG5XZEwyekVuT3RZZjB2d21RaDhFWHhMdXNY\n"
//        + "NFA3Q0ZabWtZeGQ5SDJ4ek9ON2ZIMG1lemJoLzdRaWF1S0dmZ1ZVMmFOczlQejloRzZoXHJcbnJG\n"
//        + "VUFRZXNYbkh4VEg0cGNNTTRvdW9HeUoyalhOMmwwSGJoaG5ZUTkwVy83eWxuS1BMVHUzYVZnQnZq\n"
//        + "YmZjNng4a0ttWVlrZnFYM1pcclxuc3BTb0RDNVRkMTNRdnFjbWQyM0NlTlBmcWkxOEh5V3plby8v\n"
//        + "Uzg3TDhva3A0Y3pPSFZJWFp6TEVQcmRoeTVIampxUmowZDhLY0pEbFxyXG5IRXVRLzMxNGJaS1BW\n"
//        + "Yk9pU3VmUmRIRytlSkF0LzlKeDJXMTdSb1k1a0h5VVhrbW5vZE5MUmlaNnZKeHFpQzkvZUZCOUpt\n"
//        + "dnRRV3VoXHJcbnhNU24zK2F1bVNjVERwYng5V0NZa285d2xXd1UybEZuV0JBTGNucnE1anlhVVR3\n"
//        + "cjNINGlHVzErS1hNU2h0YTNzd2xmb0k0a2Z0YzlcclxuemIrM2twbUp4NU1QNmtHUTFITEZtRnk1\n"
//        + "RHpBeHhUeW5nbklOS3gyUXlRU2UzSi9pTm4vL3dMUnFyRlY5bnkwZ21LaU1QNjVOalJWWFxyXG5S\n"
//        + "ZmJ3N2xsamRHS0xnQTFLQkRhTDlsVmFEaGNlWUpTdWZVcGpNZERjZER0QjRTQXUxY2wraSswZ1k5\n"
//        + "Sk5kU2d3T3Vpd3JFWms4anhTXHJcbllVUGdUVjFFcng1SU1hYWkxNEp4M0lueTdvRWN2L1RvOVdm\n"
//        + "c2FmZFdtNzdpQ252cmNXQWRQaVo1UnRPR3JRNUluVDF5UTdOaFhZSmdcclxuNHdlRHJOSEI2MWFG\n"
//        + "SnhyVkRMMUpjMzhJSGF4R010bFhDWHo2UnU5bEtqMkpvQ0tjaGtkUElXTFh6Q0E3b1RLbGxrTk10\n"
//        + "REF3aFhCb1xyXG5mUUdPWUU3UWJ5andPTVFVVlJ2cjVVRGN2RU9EM3BRTTROVHBPdEgwNUpGRmVZ\n"
//        + "Q1VMbUp1WWw3MllFUWpjRmp5MU5pWVQzYnlHbi9HXHJcbjdxakxyOENHODR3MnJiRVdXWGhRcUhH\n"
//        + "d2NVcTBtcHpVb0xja3dCeEFuc2VoS2lmV2xsY3o5VWV2YUo1VHlweTBPTGFIMVpHUEVlVmlcclxu\n"
//        + "MGFKNkxwQktnSlZiTzBSRmxBUjBoajdHSkR1MFFWYXRaWXRpNnYrbE1SV2FuN0FjNUhXL2ZhOW4x\n"
//        + "L2ZLOEE1ZlBhaTJvNFM3dEk1WFxyXG5qVEZqU1FqZXp0cFErREZvWmQyTU5KS0pnNEhTRnQ2QXlV\n"
//        + "dVlVY0k2N0Jod3hab1Bzc1NUWTVWdXh0ajd5aXVYaEFhTC9QeSttZHBTXHJcblFLMGtKSGNORWRL\n"
//        + "SHpBNDBmSXZhTzZRejVEY0t2T2ZPZEhob1JEQW5lVkN2UXdlNS9IR084cC9wNWhuWko4MnpzWkg1\n"
//        + "aTZEWVVOQ05cclxuR0JFU1dEeHJWOGhvUHlDc1ZKaE95U2FVTWt6MDZtYzlyM1RoTTd5cWFhUGZZ\n"
//        + "dFd6UXBLT2VKRWVTOE5vRTNQTU04V3UvQkJIeE1mZlxyXG5VbStXbFZrQ3puL014ZCtEVUI4OEdo\n"
//        + "R0dicml3bTZiRDB5dkdKTjJvYXQ1UUNhbU9sVk1OeHNSbFpIbVZYRVJtblY3Q3JEa0hEa2lkXHJc\n"
//        + "blBYSkRzMkZkZ21EakI0T3MwUkg0UUhUYkJFVkJPempQYk1sUHowdmM3YjRXQVE4ODQ5YzY2eElh\n"
//        + "bkV0aGZUekhmdDF4M1RLYng1dE9cclxub2taSzVqcmV6QWxoek92Sk5TSkpvRkVSOGN5TDdTQmow\n"
//        + "azExS0RBNjZMQ3NSbVR5UmI4VVZCL3htbkNORThWZlVPWDJaU1RrYU5QblxyXG5KWUptcHlwUFZM\n"
//        + "V1hhMUlSTDNoUEpYb2JwOUJXbDR6UjFjM1pUQ095QSswd2VpOWVHNHhrSUNNR0daL3A1aG5aSjgy\n"
//        + "enNaSDVpNkRZXHJcblVORC8wUUlNamZiMFVFbEUyMGIrcVFsWTUwTHJIRnMvc2hmbDhZeFpHZURm\n"
//        + "cmpzZ2ZlWURRWU5TMXY5MnZDclNMaFd1c3lERzlDZTNcclxudnl3elpoZ1pUdmpMSFNEOEp5WlVq\n"
//        + "dlBrTXVIK0hUbDJVa1dOVFFaRnljcVlNWTg2RWovdDdhUnp3eXVubkFoZTNkT29WTGE5U2xHWlxy\n"
//        + "XG5DdVRkYW1aNXNqeCtBOFdhV1lQYXQ3MStpK040Mm9jUGhCemtadW12NWIzRFZaL0VlRlN6VDlH\n"
//        + "cWpzcGkwQ0lacTBqY3BiTDhCQ0NOXHJcbi9KTlI1QWFkN2h2c3l5NmplYWU4SUg1bkFTVEJRM1Rz\n"
//        + "NDN4blFEaTF4bi9EZldqMVNFTkhTeFNYVWJaa1MwNytnZUNVR1E2T1hvUEhcclxuVjJYVWhYRkkr\n"
//        + "SERiUjB1cDkwZTBoNU5sVTdXUmVDYnhoV2diZWlYV0JoaEluNHdqUURhekl6ZzMrc3FTV1RJZjFG\n"
//        + "bHk0NTl2dG5nS1xyXG4vZ0w3S1V1d1Q4RjJhYm5YbU1ZS1NZb25BUmVjaSswZ1k5Sk5kU2d3T3Vp\n"
//        + "d3JFWms4aW16ZEhUYXRvMmthbnZnVzdsckJvZ0tPQ2IwXHJcbk82eTZacVVNQndGTTl0SE5YZkFY\n"
//        + "dFRqNTJtRGJVRnhETjVmVWt1cTVHWVhaOW9BZnBuMWlsS3B5dWE0N0FWcG10aFprV1cyVFVyYmRc\n"
//        + "clxuMm9xajQ0Y2FVQ0gxRTB2dDdpTzFGTElNYk1jNkd6NDhwdlBtaHpzR3BtUllXZkcwaDVObFU3\n"
//        + "V1JlQ2J4aFdnYmVpWFcrTlR0VXdnbVxyXG5LZEZrenpHT2dFeWdCZUVnVWJocG9lUDBvTEhLdnF0\n"
//        + "L2E5WXdoWEJvZlFHT1lFN1FieWp3T01RVVZSdnI1VURjdkVPRDNwUU00TlRwXHJcbk90bTFwOWlY\n"
//        + "YlhBWHZ3clpyMjJ2VTRqYy9wazRVcFBRZHJaM0h6MlJwY3hzcjhDRzg0dzJyYkVXV1hoUXFIR3dj\n"
//        + "VXEwbXB6VW9MY2tcclxud0J4QW5zZWhLaWU3ckRQTDlWVTh2UGRQbGRaT3B6d2Y5ZEFoaDdoRVhR\n"
//        + "RWVBUVJyZjdHVW5BeEUxV2lDNHZwSmFJNnkza2hqNDFtMFxyXG5oNU5sVTdXUmVDYnhoV2diZWlY\n"
//        + "V1B4N2NoSzNlTGRsdmFqV1pjak5NREVtQlJERnpuYk52MjRiSUlmTXNEalFvbVdjSitYbWw4a1Fn\n"
//        + "XHJcbk9IOWZWRFI1cnJNZ3h2UW50NzhzTTJZWUdVNzR5K0JUYzRpRzlKSTdkTDNLeGt5ZXAyQzR3\n"
//        + "UUxDSXNSeTlaYmgrUlRibHc3V2JZUXpcclxueXEwR2RhMUJSMGRja05QRTVseXVHM2YxekpWZjY1\n"
//        + "MWgvcU9GSkgxQ2gwSVE3Nm1SN2tKcGdpOFpjbmVPaSswZ1k5Sk5kU2d3T3Vpd1xyXG5yRVprOGxh\n"
//        + "TW13YUxjQnJFbFpqamNPRmMwc3l4bDZ3N2lPU1hQdE0vU3g1bXNuZ1gzODF3RDQxRWEvdFJ3c2ht\n"
//        + "d3VCanVFdk95L0tKXHJcbktlSE16aDFTRjJjeXhENDF6UlB6YXZWTS9kbk1nUXduQWxKSy9COU5T\n"
//        + "MXlvS2tpMFNaSzRLQUlrSHc1SW5UMXlRN05oWFlKZzR3ZURcclxuck5FUitFQjAyd1JGUVRzNHoy\n"
//        + "ekpUODlMU0xjQWgwcG5DOXRSR0gyRU9FSlhsQTVuWVNGMHlzeThBR2MzN0c4VlN6VFJlbW5GbWIw\n"
//        + "eVxyXG5MMDB0TUwvakw0eEVkVnJlbjhzSlpSakMxRlNwamVWWDhtclMvS3JEeWp4K3Q5cUpzLzY1\n"
//        + "SElXVlBiR0hNZG5WSjhxcjJFTFRxVHY1XHJcbm9uNHVsakdQZWJoT1ZtUlk5S1UrenAyd1VZNXJj\n"
//        + "eGtnREhrRVF4TXg1ZzZNQ0pNM001VDduZHBRNG5Kd3NkTDBqZGk4N2hSekVHOTFcclxuMDY5ZSsz\n"
//        + "c2dOZXE1R1lYWjlvQWZwbjFpbEtweXVhN0t1eCtmYUVVcElrY0lsRzhVaElFMXAyMkxUeTc0T1Js\n"
//        + "T2N4aHdqQk1FdnV2L1xyXG5qSThOL3BzeGp1Q3RvdWxXRklnbUtJSEJFT2xhZ3kycHd2d2h4blZv\n"
//        + "WGQyYURSclRsWXE3MzI0c00rY08rM0ZGTHlJVEt4MGNJaWMwXHJcbmcxc2xBb21rVU1paUxJYW1X\n"
//        + "alpJY3VmdUhXUmttb3k2bEM0Z3VHSW1PQnNvaHRVVU5iejN6VlMrVTZ5c3RRWFE4bHhCZXZOODhK\n"
//        + "ZTBcclxuUDZ4STlxVGtZWm5SbThKc2dLQUt3ZnIzaWVDSkNuMjZ0cW9oZ1Nxekp0Z0xwODJUWUtM\n"
//        + "bFFsbUxUMktvVE03VXhqVklEVnUzTVhaR1xyXG5hTFpITHA1TlFLc1IzZjE5aW9MYlhzRm4wSkkr\n"
//        + "NFJUOFlHbno5dHREQzE5cFVWVkx6c3Z5aVNuaHpNNGRVaGRuTXNRK255TVNjTkh6XHJcblhsT2dv\n"
//        + "c0J4UmhBMk0yck14NTNyN2ZOQmd4MUNmZ295S2xoNGNrdEdySUgxOUxEc05mb25oTHBJcFZGQjl5\n"
//        + "Qm9HL1pqU3hxcE5WMUZcclxud2xqT1lzWTRLM24vYzZIZllNcGNmL0pPRW0zazdXaUxpOVpNdWIw\n"
//        + "dzZIZTZBdlE5T3F5ekUrSk5xMEJXb2ZUUWV1YkpnVFpjUjFRN1xyXG5RNmN0SmE1R1JwUTk5b0JM\n"
//        + "bDFOYUZROWNITUhCVnlqVEJoOHBUUkl1TnFDUGV1OWpIaGozMjFBQnZUWnVVSFRDeWR4RkJVM3JM\n"
//        + "eTZMXHJcbjdTQmowazExS0RBNjZMQ3NSbVR5ZVl3N3o4aitoNlBHY1M3M0s3alhmdG55OG50dEJj\n"
//        + "bUJpaG5zbWhQb0NjKytwUDFiaElhYmlBK2Zcclxuc3NBMnpScFlkTHhRVG5WRXJhZ1N1OGs4Mlds\n"
//        + "TThqQ0ZjR2g5QVk1Z1R0QnZLUEE0eEJSVkcrdmxRTnk4UTRQZWxBemcxT2s2S1dyTVxyXG4rbFFw\n"
//        + "bUFVZHZuRWVueFhzNUZHVkpxUFZ2L09Kd3JjSkhZRTJIdktjZ2ZqWFB4T0QySXhrRTBLbEZDRHk2\n"
//        + "cmtaaGRuMmdCK21mV0tVXHJcbnFuSzVyb00yQ1VSR2VBcVc5MWYrbXRMc0c2SFZQbm5uQ3JvMTBv\n"
//        + "QjJ5bWxHNUtSUUxEblk1U3VQUTVQOWtGeWJoSDV5ZkovcDVoblpcclxuSjgyenNaSDVpNkRZVU5B\n"
//        + "ZmFSM2hmN1dDUGpQTFZhd3VYVEd2Q2N0TWU2UGd4N2NNVjk2TVNXbjV5TjRXdVNhZURIUEJLa2Nm\n"
//        + "bXVWZ1xyXG5BYVNGS0hWbmtDQ0VmeFpvWDhONzg4OElDOWhVdFR2eG5maGhFQUV1V0xyWnFPL0J2\n"
//        + "VGhzZ0ZoWTVJSEM5YjI4T0phMjBvWGNDQjJQXHJcbmczQzRHdHlOL2ZmOGF0TDhxc1BLUEg2MzJv\n"
//        + "bXovcmtjaGRRTDVqRCtHTFNuT25ldTlxWDdKYy92b2FuVXJRVHhoOVNKOVR4NVZVODJcclxuOWVj\n"
//        + "b0Q3bVFBaEJKM0dxbngvVDQxVENGY0doOUFZNWdUdEJ2S1BBNHhCUlZHK3ZsUU55OFE0UGVsQXpn\n"
//        + "MU9rNm9ySHpMcEt2M01SMFxyXG5BMG1rTW5mSkNIcmdUdThTVmVCbGRibXM2WVQ4VDRHdndJYnpq\n"
//        + "RGF0c1JaWmVGQ29jYkJ4U3JTYW5OU2d0eVRBSEVDZXg2RXFKOTdCXHJcbmJvRURDVmJDUVpyaWc2\n"
//        + "T0NVWmhPcVdSK2xuQkErcTUxUHVkRGhYbFVEa2lkUFhKRHMyRmRnbURqQjRPczBSSDRRSFRiQkVW\n"
//        + "Qk96alBcclxuYk1sUHowdWZZYXlqYnNQTW51TWthck15b0oxOHIxaENIZ2xDbXY3Uk9UZHEvL0tN\n"
//        + "cnd6TEo1SGd0NjdQbzd3Ymh6NlVKN3lGS0hWblxyXG5rQ0NFZnhab1g4Tjc4ODhJVnZyWWlxblgr\n"
//        + "K25pa3pvQVRWVVBWa0lTWjF1VDYxRmQ1UjFCUE9SNjByY3lKaDRjOGFnWGVnQjZxUklUXHJcblR6\n"
//        + "RU96RFBGcnZ3UVI4VEgzMUp2bHBWWkFtS2w3SWZ0WGhtMU9uOWo2UmRtS0tCZVVuQzdoS3FOMDh0\n"
//        + "OWFpS0d6eHkyY0IrNG9rSXJcclxuQUs2N1kwa25sU2dIbFUweDVZOWJwMXVuS3dJKzQvMWhKQWkw\n"
//        + "aDVObFU3V1JlQ2J4aFdnYmVpWFdqVFM5UGZIVFlTM1NOazRSWm5EWlxyXG53V2lYcHp1OVJkTmx2\n"
//        + "U1JFWm1WbDViMWI4SDZZYlBPS01XZlZRN2hKa3l1enpEUEZydndRUjhUSDMxSnZscFZaQW1LbDdJ\n"
//        + "ZnRYaG0xXHJcbk9uOWo2UmRtS0tETnRVOE4vaHoxMnZlcm5nRTZydGxEWU5YTkhIRGdmSWNrZVp3\n"
//        + "UGp6UlUvT1VHWGI1SlRFWmY3NGZDU1prSVNaQ0Fcclxub0FyQit2ZUo0SWtLZmJxMnFpR0JLck1t\n"
//        + "MkF1bnpaTmdvdVZDV1l0UFlxU0xCMXhkd0VZclRlZEpmMlNUcnJoVCszeFhFTWNnSnZmc1xyXG5N\n"
//        + "N3Q3VlkxZWg1bU1XYzNIWVRldUpBMHdWaWxGZE9xNUdZWFo5b0FmcG4xaWxLcHl1YTd6SmpGN0la\n"
//        + "OVRsRk94V2hBcDBvSFk4OUNkXHJcbm5YaDhlWkNnWkNwaFdtRGxTS3VqY1Z0U3RHL2wzdWxreko0\n"
//        + "S3VUNmY2ZVlaMlNmTnM3R1IrWXVnMkZEUS83Sm5HOVVwQnVlTmdmN1pcclxuam9tbzBmTUIxMkFj\n"
//        + "SnFZYmFBVVIvUnMwYVh0V3VkV3BCOVpabG1HRXZvdWZNbEcwcjhDRzg0dzJyYkVXV1hoUXFIR3dj\n"
//        + "VXEwbXB6VVxyXG5vTGNrd0J4QW5zZWhLaWVxaFlFVzRoMmhSdk5jbHlSWG56b21UQndQZHJ2a0FX\n"
//        + "RmEyUU5zQ05wRHBYemZzTGJBUlIxNWRtdVlGZ1hKXHJcbnBDeWY2ZVlaMlNmTnM3R1IrWXVnMkZE\n"
//        + "UTJ4SXV6T2IyS2U4RUt6RXkwaGptMjBTVXhlazhoS09reEpMMjFmM0Y5eUVPeXlaU1dyYm5cclxu\n"
//        + "Q1h5VGcwZmJoQVlCZnI4QWhCK2I0aFh2RHBkUlNNMHBZMElYaUhPWi9ncjFmT0IyQ1JhRUR4L2Vt\n"
//        + "QTVkN0tvVGdNVFVZSTBEalBuYVxyXG55bDBNUkN0eTdNN1dFV2lWTUlReGdtOXJpQ2VJRVh2K2E3\n"
//        + "dVJVQmt2ZkhEOERqQW9WUkFtaFk1MlZ4bitlNHNpamdvMnRNalRRR09LXHJcbmFIVElDcmtQQjh3\n"
//        + "enhhNzhFRWZFeDk5U2I1YVZXUUppcGV5SDdWNFp0VHAvWStrWFppaWdXUVcyRHVpSkFCVEZLYk54\n"
//        + "WDZtYzZUZWFcclxuSzRoTnRMNXV4eFVSVU4wb3FKVGNlbnBLOEN4UTgrWnZnVEQ3UmxoeVBITGUy\n"
//        + "OFhzZmdscnZOemd1enpESXEzN2NIS1JyTU1DUk4vK1xyXG5JNjF0M1NXVGhWbWVBSGdRai9hYURJ\n"
//        + "bGUwQmR0Z0tBS3dmcjNpZUNKQ24yNnRxb2hnU3F6SnRnTHA4MlRZS0xsUWxtTFQySXdDTDA3XHJc\n"
//        + "bnF5VDdkZjZ5NndvYnVaZmNXYUdMN0lkRE5IRXJ2cnFGeU5xZlZIU0RxTUlIUUYzNER1MVFiN1Va\n"
//        + "K3VkTHpzdnlpU25oek00ZFVoZG5cclxuTXNRK3pFVDZwL2hiTkZtME9XdFlJQjJtUlZKOXlrM1o0\n"
//        + "OWVST1kzNGlMU1FEUTU0ZXpUWWpxYlorVkF1UitnWDdJKytMcnpnalRDNVxyXG5lZU1WS2xGWktu\n"
//        + "bk16SXZ0SUdQU1RYVW9NRHJvc0t4R1pQSmhKdndqUHZxNUZUVlZtT3JQUi8rYjYvRWtzVnhqWXND\n"
//        + "S0VPa2pCejdwXHJcblNDSHgya3R0Y0NsbGloTS9vYVgyZlBuM1dqRG5TVFhKaVVJSjRHUk5JWWtw\n"
//        + "Q3dxbW9nS2tyZnpOTzd1dVRQTFNnWFpyak15bFUyTTFcclxuVzhrUXYyazNxV3paenNlVmZOTmpE\n"
//        + "bG5SOHZGOTFaZmJRdnkxTGpGTGVmdG1JNmM4Y3lWMDVKK3IzeFF2TFdsaU8vdmlGWC93L2szVFxy\n"
//        + "XG40RnU5S1JYSnQ4UnhzUTZHcnI3YUFhUkN3UTBVdzBleGYvd3F2YzAraDFQREx3ZFVJNEFFTEhx\n"
//        + "WnJyVWNKQzFWRyt2bFFOeThRNFBlXHJcbmxBemcxT2s2Wks2SWFLemNSQ0xPOGs0K2E4RXArbmFU\n"
//        + "aWhIU2xkVGlSN2hZZWpBZ1JxTUR2RDhEcEdJUFZVdWtVTU9RYTV1bGF0TDhcclxucXNQS1BINjMy\n"
//        + "b216L3JrY2hkWThsVmZlcncwYWsvQ1MrOVA5UHVOOHowbnFSaVNEckg0b20zTVZvS3BVN2I4N1Jk\n"
//        + "MzREdGxrQ3BtdFxyXG5uNFZOMVhlTGFaQmh5dVJKOTFMRzJXUStpTG11c3lERzlDZTN2eXd6Wmhn\n"
//        + "WlR2akxZdXNxc2tKMXZwckt4M1pWNFVqN2h3T05Gcm9tXHJcbjRzSjkxWnVSR0RlejI1R01OQjdZ\n"
//        + "Yi9LS2ZNWE5pdGRtWTVtYTZya1poZG4yZ0IrbWZXS1Vxbks1cmhCUTlkcmx6WDl6WVFrM0Vya0lc\n"
//        + "clxuTHNjOTBIeWlCbTQvekNoM2lZd0FOeGxoUnFHbHUxTUdac2M1TUIzckQ3eFJBNS9wNWhuWko4\n"
//        + "MnpzWkg1aTZEWVVOQnhKRW1vY2FqVlxyXG43TWdEampVQVZyZEQrZ0RXOTlzMjFXOGdLS3JPMVUx\n"
//        + "dUpjajl3R1N0clNjK3JoaXFDMzFlL3pxdXN5REc5Q2Uzdnl3elpoZ1pUdmpMXHJcbk5INmo1dlB1\n"
//        + "L0U2RE5kcXdLeTk2QUFPTkZyb200c0o5MVp1UkdEZXoyNUVsOU9UMldvdFk4SkV6MEpwVjUwSGJh\n"
//        + "dEw4cXNQS1BINjNcclxuMm9tei9ya2NoZFk4bFZmZXJ3MGFrL0NTKzlQOVB1TmFPL0p2V1RKUzd6\n"
//        + "Qmd6Zkliejg4Q2Niby9qd2dNKzdJQ254VGdxTFU4S0ZYZ1xyXG5kRElEdUluelJRczdKd1RzK2Fv\n"
//        + "VmNiWGVyc29QMkxlUDBxU2lNdjFoc2d1cTdzbjNMNDFLRVRLbTdQT3ZHRGNIRWJNSm1VdURaSVVC\n"
//        + "XHJcbjVvRWpnVXpoUks5c1h4TURpTlhTb2c3QWY2NkVhMXdmbEJJeExyWjYvUVQrKzQ0YWc1L3A1\n"
//        + "aG5aSjgyenNaSDVpNkRZVU5DRmdhc0xcclxuaTNZVVdxVWUrL1FnUnFmclAwSEMxTDZEV2VDdVlC\n"
//        + "NUt4c3M0Vk9XOTVzbFZLTFpSSUV0WkZuNzE3d211c3lERzlDZTN2eXd6WmhnWlxyXG5UdmpMTkg2\n"
//        + "ajV2UHUvRTZETmRxd0t5OTZBQzl1dmhSRm8xWG9XbkZGRE14alZvSW96dnVHUWgxaEJRckpLUFdx\n"
//        + "Ymw3QXAydjJSVGZJXHJcbkN4Ry9lR09ZQ1c2cG9KYmxxWEkzR0JEOTFWVWZKeUZkdFhDMGg1TmxV\n"
//        + "N1dSZUNieGhXZ2JlaVhXMVNNRUtNYmxoMS85alVjbzNjd1NcclxucmRUOWxLS2M5R3NDNkFSQlVP\n"
//        + "R1ZoN29EMGlEQktraHlwY2lyS2hiM1ZnalU3NDBaclVabVAwcnNFMERabVFQOCtrZDkyZ0RGMktN\n"
//        + "L1xyXG5zWE5MYU4yOVBWUkNTRFovd29mSnByaGpaQzhlZUkzT2hTaDFaNUFnaEg4V2FGL0RlL1BQ\n"
//        + "Q0pwOXFoVGZ2eWFQUG11a1RhRWtZR0RPXHJcbkVQcjY2K3Z2V2FOczBCdjFHMjh2SFpJUFMyT2VE\n"
//        + "dFVnQitHcUE2YWlFZDVObmFFc3RnVlIvcGovTDdaS3hwcWY2ZVlaMlNmTnM3R1JcclxuK1l1ZzJG\n"
//        + "RFE4WW9rNnd6L3Q5VmZnNkxXSHlDSnpaUHpMQ250cWlGSkhEMW9aelByK2VwM25ENzQ0TlpiNm9L\n"
//        + "eThydmJsL09KaFNoMVxyXG5aNUFnaEg4V2FGL0RlL1BQQ0RVNDR2MDRXQWU1RnVjd1dBVHJGUkZB\n"
//        + "UVJ2cFVJUEtqbXZvVDNvZWNiZjJMdVd2ckZ1QXBWQlM0dkgwXHJcbmp0cms5T3E1R1lYWjlvQWZw\n"
//        + "bjFpbEtweXVhNERLcTkyckZYNGIwbnNIV1NjQXVRWk5BWnAyUVpHZ0VleU45bVR1THpXUXBHcHhp\n"
//        + "cHNcclxuQVk5UEw0NHpuTmUvZGtzN1p1WDdnZHc5djZJNkhPejF0YkNBekRQRnJ2d1FSOFRIMzFK\n"
//        + "dmxwVlpBbmh0aHdiUzQvWjcvaFpuNTA1dVxyXG5XQWhMQ3VxcXdkS2MyUW9GdVNKZFArRjVjRHJq\n"
//        + "YzFpNE5NVmYzZm5lb1JFSWdtclMvS3JEeWp4K3Q5cUpzLzY1SElYV1BKVlgzcThOXHJcbkdwUHdr\n"
//        + "dnZUL1Q3ajlvMG9vT1Y0VWJsUDVIditMaVh4SEV1dEIxbFNjakJnSFkrSmJCRVNXNUZWNEhReUE3\n"
//        + "aUo4MFVMT3ljRTdQbXFcclxuaEo0Z1JEa2p6MFVwb2R6R01HanlqVlJJUUVLTWMyVTlQQ0QyN1lQ\n"
//        + "dzhzWDRHZ0hiNit6VjNwRk5mTGkvL1BCSzJ5OVYwcUhEdWlHelxyXG5JOFpCdm5HSm1BNUluVDF5\n"
//        + "UTdOaFhZSmc0d2VEck5FUitFQjAyd1JGUVRzNHoyekpUODlMZ1hPNENNUFhjVjg2M3B3TFNLK0dX\n"
//        + "dW14XHJcblRDRWdwUEI4VDhQeXhsWU5rb2hVUXlOYlNLdDhOTFJGc3MzZHE3MmVpczJMNDhXMGV4\n"
//        + "NjZJVndnV1Z5dmFyKzdmTXEzdjR6eXdwTC9cclxuSjdLRmdoWkMvTFV1TVV0NSsyWWpwenh6SlhU\n"
//        + "a242dmZGQzh0YVdJNysrSVZmL0QrVGRQZ1c3MHBGY20zeEhHeERvYXV2dG9CcEVMQlxyXG5EUlRE\n"
//        + "UjdGLy9DcTl6VDZIVThNdkIxUWpnQVFzZXBtdXRSd2tMVlViNitWQTNMeERnOTZVRE9EVTZUclRJ\n"
//        + "MlN2aUFGMnRsdW5HOGJVXHJcbk1KU3VlNG55ZTRPUzN3WnNiNExwV2RtV1NhL0Fodk9NTnEyeEZs\n"
//        + "bDRVS2h4c0hGS3RKcWMxS0MzSk1BY1FKN0hvU29ueDZHak5qbXRcclxublRjUHppeDIrZTlZWms1\n"
//        + "NnRaTFRFSTB2cGxnYTVPUDhHSU5qQXZTT3h3UVhmbGdwREE0bSs1enludHlmNGpaLy84QzBhcXhW\n"
//        + "Zlo4dFxyXG5JRXkyUHZBOC90a2NmL1Y4UXQ2WlQxaG5LVmFqbHEwODBiQ0NkdTJTUkEvOE1JVndh\n"
//        + "SDBCam1CTzBHOG84RGpFRkZVYjYrVkEzTHhEXHJcbmc5NlVET0RVNlRxYlQ0L3dJSkV5ZmJJL1hC\n"
//        + "UDdZVlVGYWdoNzV0blFJUnR4dGM5RWVOSVhOaUoxUUZ6YlJFQmdwUjBXV3JScWRlZnFcclxudVJt\n"
//        + "RjJmYUFINlo5WXBTcWNybXV1RkZNcHZRS0Z3bHhhMmpYeHNXOGovcldFcEZ0VTJmWURDUUhOZ0hF\n"
//        + "dWUvaXJ5VzBxNUtIOHd0V1xyXG5kWHhMUWlKaEdhSytCSWhuTTJXeno5Y1VocmhZRW1jY05BMm9Z\n"
//        + "VXl5QkV6RUZUWjQyWkNZV0ZTYWRNWUZac1lValJlQTBSREpBdlE5XHJcbk9xeXpFK0pOcTBCV29m\n"
//        + "VFFldWJKZ1RaY1IxUTdRNmN0SmE1R1JwUTk5b0JMbDFOYUZROWNITUhCVnlqVEJoOHBUUkl1TnFD\n"
//        + "UGV1OWpcclxuSGhqMzIxQUJ2VFp1VUhUQ3lkeEZCVTNyTHk2TDdTQmowazExS0RBNjZMQ3NSbVR5\n"
//        + "RFJ6VUd6Q0dBNEl5ZjQ2cGpHOER2QWZQelk4VlxyXG4wdU9DV1h2aE5ZRzJUVFhmelhBUGpVUnIr\n"
//        + "MUhDeUdiQzRHTzRTODdMOG9rcDRjek9IVklYWnpMRVBwM2NBcjIzUGROdm4zYllKcGFUXHJcblpQ\n"
//        + "TGl5K1pXOUxGZGNXK3orYy9hMkpmUjh1TklGRURxVm1aeUlFcEJCdFdOdko3Y24rSTJmLy9BdEdx\n"
//        + "c1ZYMmZMU0FQZGNkdDgyQi9cclxuMlkxd3lDc0owOUh5R0oyczZtWXA0U0NxeU9iMk5zQjFnYjU0\n"
//        + "WUVaMk8yakdTSXZZTXpjZklnMFlyR1p5QzI3aGY3Q1VUZGI5WTJseFxyXG5aOW4wQXBxNm1KZTNF\n"
//        + "WnAzTzJ5cmdMSlhkdk0rRVJYU2tLdmZQY0pMM09mZU16Lzl3U3N0M1NhdVpWNlBlQmJqYW81ektK\n"
//        + "OWdWZGFpXHJcbmtLTGxGeEdJeXFSUXlLSXNocVphTmtoeTUrNGRaR1NhakxxVUxpQzRZaVk0R3lp\n"
//        + "RzFSUTF2UGZOVkw1VHJLeTFCZER5WEVGNjgzendcclxubDdRL3JFajJwT1JobWRHYndteUFvQXJC\n"
//        + "K3ZlSjRJa0tmYnEycWlHQmNoVHFFMndpZ2k2SHR3ZEk1Qktqb0kyQUVGM0M4ejErSkJVRFxyXG5V\n"
//        + "ampPTGpyTjBJQjJBd2E3T2pxb2NrdnQ0a2Q1YnpFSjVUZkJCNXNNQWM4c1czbWJEZjFqYk9ybkYv\n"
//        + "QmVWNkJKZlhkUTY5UEtzK1hyXHJcbkl4OXVPMTdpTEF2OXpXNVlhdEw4cXNQS1BINjMyb216L3Jr\n"
//        + "Y2hjanV0UTZFUy9TWkx4UTFFOXpqNHREdm9hblVyUVR4aDlTSjlUeDVcclxuVlU4MllWVm1FWnhy\n"
//        + "VzJnc2tGVTRWaGxTTWl6RlNlMnZDVlc3dUVEMTZsME1PN0tMN1NCajBrMTFLREE2NkxDc1JtVHlG\n"
//        + "anpCT0lRbFxyXG42SjJPbEVERTk3QlBtak45b1hiM2V0aVQzRlJSOHNrS3hqS1RhWlA0d1ZJVDVH\n"
//        + "bWc4NGM0R3A2ZG5ydmNXRnFFdUdHU3lWMVF5NjVVXHJcbjdyU0hrMlZUdFpGNEp2R0ZhQnQ2SmRi\n"
//        + "NG1yZ3llbjJnWldOSU85aVE3M3Zkak5CcC9HYzhZSFlIV2ZORGphVEN5bTdjRmpDMTVxamVcclxu\n"
//        + "d0Zwck82aUVRSUdMN1NCajBrMTFLREE2NkxDc1JtVHk4bElwK1E4bDJxVDJld0kvbEoxdG9PTG9k\n"
//        + "ejExSC94MzA5aE42aHpuOURoM1xyXG5Jdm8rNTlEcFlmVlFZTmFlOTRsTFM4N0w4b2twNGN6T0hW\n"
//        + "SVhaekxFUG9DYmJDRUpwdUVicGZiSjNYU0NHcjZRR0cwb0tjaEZSWE1lXHJcbkdZYWNPTUFxNTdZ\n"
//        + "dno3Q3lPakd6RFpLZlptYWNNWUNnQ3NINjk0bmdpUXA5dXJhcUlZRnlGT29UYkNLQ0xvZTNCMGpr\n"
//        + "RXFPZzNPUldcclxuUjhhZG9FTU9oMnMySTVJdDBod3lnSWZlREk4cVU3U3l5Z3R3eDJveU4zQU1x\n"
//        + "WUliTzdGTUtPcU5TaXdoYXRMOHFzUEtQSDYzMm9telxyXG4vcmtjaGNqdXRRNkVTL1NaTHhRMUU5\n"
//        + "emo0dENpZmk2V01ZOTV1RTVXWkZqMHBUN08zK1lXVFY5OXVsSm9Uc0FyOVJ6N3lKL3A1aG5aXHJc\n"
//        + "bko4MnpzWkg1aTZEWVVOQ2FvVmFRd0VYVTdiemxkRXRSQ3Y3Z3hOejhaUmhMU1d4NXZVMFVRUHkz\n"
//        + "bUc3ODNlOE9yb25SZFdrQWlrNERcclxuUVBndTdjTEVIUmtId1RCQ0lucnMycVE1XHJcbiJ9\n";

//    String s = "eyJoZWFkZXIiOnsic2lnbmF0dXJlIjoiWlUyU3lzdnRIVzJKZWtUS3ZTVW5kRXAraWl4bWJyNWpk\\n\"\n"
//        + "        + \"Wjd3UWQwRjhZTVExYXdTVnNERWlqc2VLU1FPd1Y2OVIyS2VBZXo4UjBoWlxyXG43dHNDVU00a2Mw\\n\"\n"
//        + "        + \"VmFLU2g3TDU4U3g5Qms2ZSs4RTlDTzRIeUV0V3JZdk9OTitLWDRsNnhUWnp1SmxSakdRdUliamU2\\n\"\n"
//        + "        + \"VmRGRW1yNlVoXHJcblhwQk5abEt4NEpiQXFvR3FHV0k9XHJcbiIsImNhbGxfaWQiOiJaREdSc3d3\\n\"";
//
//    System.out.println("字符串长度："+s.length());
//    System.out.println("压缩后：："+compress(s).length);
//    System.out.println("压缩后内容：："+compress(s));
//    System.out.println("解压后："+uncompress(compress(s)).length);
//    System.out.println("解压字符串后：："+uncompressToString(compress(s)).length());
  }

}
